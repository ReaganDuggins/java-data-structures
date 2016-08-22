import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class DijkstraHighway {
	
	public static final int INFINITY = 999999999;
	private Link[] adjList; // first dimension lists vertices,
	double[] distances;
	boolean[] settled;
	int[] prev;
	String inF;
	
	public DijkstraHighway(String inFile){
		inF = inFile;
		try{
			Scanner scan = new Scanner(new File(inFile));
			String line = scan.nextLine();
			//use the first line to make adjList
			adjList = new Link[Integer.parseInt(line.split("\\s+")[0])];
			
			while(scan.hasNextLine()){
				line = scan.nextLine();
				
				
				
				if(line.split(",*\\s+").length >= 3){
					
					if(line.split(",").length > 1){
						//line is a vertex
						String[] parts = line.split(",*\\s+");
						Vertex v;
						try{
							v = new Vertex(parts[0],Double.parseDouble(parts[1]),Double.parseDouble(parts[2]));
						}catch(Exception e){
							continue;
						}
						//place the new vertex
						
						for(int i = 0; i < adjList.length; i++){
							if(adjList[i] == null){
								adjList[i] = new Link();
								adjList[i].addNode(new Node(0,v));
								break;
							}
						}
					}else{
						//line is an edge
						String[] parts = line.split("\\s+");
						try{
							int v0 = Integer.parseInt(parts[0]);
							int v1 = Integer.parseInt(parts[1]);
							
							
							adjList[v0].addNode(new Node(0,v1));

						}catch(Exception e){
							e.printStackTrace();
							pl(adjList.length + "::" + line);
							pl("Error!");
						}
					}
				}
			}
			scan.close();
		}catch(Exception e){
			e.printStackTrace();
			pl("Unable to open input file.");
			return;
		}
		distances = new double[adjList.length];
		for(int i = 0; i < distances.length; i++){
			distances[i] = INFINITY;
		}
		settled = new boolean[adjList.length];
		for(int i = 0; i < settled.length; i++){
			settled[i] = false;
		}
	}
	
	public void printResults(int start, int end){
		
		int cur = end;
		String s = "";
		String verts = "";
		String edges = "";
		int numv = 0;
		int nume = 0;
		
		for(int i = 0; i < adjList.length; i++){
			try{
			Vertex v = ((Vertex)adjList[i].head.getVal());
			verts = v.name + " " + v.lat + ", " + v.lon + "\r\n" + verts;
			numv++;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//prepare to print to screen and to file
		
		
		while(cur != start){
			// print cur
			s = adjList[cur].head.getVal() + " => " + s;
			
			// print the vertices
			Vertex curv = ((Vertex)adjList[cur].head.getVal());
			verts = curv.name + " "  + curv.lat + ", "  + curv.lon + "\r\n" + verts;
			
			// now the edges
			edges = prev[cur] + " " + cur + " " + ((Vertex)(adjList[prev[cur]].head.getVal())).name + "/" + curv.name + "\r\n" + edges;
			nume++;
			//go to next vertex
			if(cur == prev[cur]){
				s = adjList[start].head.getVal() + " => " + s;
				break;
			}
			cur = prev[cur];
		}
		s = adjList[start].head.getVal() + " => " + s;
		
		//print to screen
		pl(s.substring(0,s.length()-4));
		
		//for writing to file
		try{
			FileWriter fos = new FileWriter(new File(((Vertex)adjList[start].head.getVal()).name + ((Vertex)adjList[end].head.getVal()).name + ".gra"));
			fos.write(numv + " " + nume + "\n" + verts + edges);
			
			fos.close();
			
			pl("Wrote to file: " + ((Vertex)adjList[start].head.getVal()).name + ((Vertex)adjList[end].head.getVal()).name + ".gra");
		}catch(Exception e){
			pl("Could not write to file.");
		}
	}
	
	
	public void dijkstra(int start, int end){
		//initialize the distances
		prev = new int[adjList.length];            //has the previous node's index
		
		for(int i = 0; i < adjList.length; i++){
			settled[i] = false; //0 is unsettled, 1 is settled
		}
		settled[start] = true;
		distances[start] = 0;
		
		//head of each list is vertex, other nodes are int of the other vertices
		int curi = start;               // the index of the current node
		Link cur = adjList[start];      // the curent node's list
		while(!settled[end]){
			// update connected nodes
			updateConnections(curi);
			
			// end updateing for this node
			// go to next node to visit
			settled[curi] = true;
			int tmp = curi;
			curi = getClosest(cur);
			if(curi == -1 && tmp == 0) break;
			if(curi == -1){
				// no more connections, go back
				settled[tmp] = true;
				curi = prev[tmp];
				cur = adjList[curi];
				continue;
				
			}
			cur = adjList[curi];
			prev[curi] = tmp;
			//end going to next node
		}
		printResults(start,end);
		
		
	}
	
	public void updateConnections(int index){
		double curVDist = distances[index]; // this Vertex's current distance from start
		Link cur = adjList[index]; // the current Vertex's list
		Node n = cur.head.next;    // the current Vertex's first connection
		while(n != null){
			double distToCon = vToVDist((Vertex)cur.head.getVal(),
										(Vertex)(adjList[(int)n.getVal()].head.getVal())
					);          // the current Vertex's distance from the current connection
					System.out.println((Vertex)cur.head.getVal() + " -> " + (Vertex)(adjList[(int)n.getVal()].head.getVal()) + " = " + distToCon + " != " + distances[(int)n.getVal()]);
			//update distances[] for this node
			if(distToCon < distances[(int)n.getVal()]){
				// if this new distance is less than the previous distance, replace
				distances[(int)n.getVal()] = distToCon;
			}
			
			n = n.next;
		}
		
		//update a node
		
		
	}
	
	public int getClosest(Link L){
		double shortest = INFINITY;
		int shortestI = -1;
		Vertex v0 = ((Vertex)(L.head.getVal()));
		Node n = L.head.next;
		while(n != null){
			Vertex v1 = (Vertex)adjList[(Integer)n.getVal()].head.getVal();
			double x = vToVDist(v0,v1);
			if(x < shortest && !settled[((int)n.getVal())]){
				shortest = x;
				shortestI = (int)n.getVal();
			}
			n = n.next;
		}
		return shortestI;
	}
	
	
	public int getIndexOf(String vname){
		
		for(int i = 0; i < adjList.length; i++){
			if(((Vertex)adjList[i].head.getVal()).name.equals(vname)) return i;
		}
		return -1;
		
	}
	
	
	public boolean isComplete(boolean[] a){
		for(int i = 0; i < a.length; i++){
			if(!a[i]) return false;
		}
		return true;
	}
	
	
	/****Methods to simplify printing****/
	
	public void printAdjList(){
		for(int i = 0; i < adjList.length; i++){
			if(adjList[i] == null) continue;
			pl(adjList[i]);
		}
	}
	
	public void p(Object s){
		System.out.print(s);
	}
	
	public void pl(Object s){
		System.out.println(s);
	}
	
	public void pl(int a){
		System.out.println(a);
	}
	
	public void pa(String[] s){
		for(int i = 0; i < s.length; i++){
			pl(s[i]);
		}
	}
	
	public void pa(int[] a){
		for(int i = 0; i < a.length; i++){
			pl(a[i]);
		}
	}
	
	/**End print methods**/
	//Following method was written by Dr. Hunt of Covenant College//
	
	double calcDist(double lat1, double lon1, double lat2, double lon2){
		// calcDist take two latitude / longitude pairs and returns the
		// distance between them in KILOMETERS
		double R = 6371; //km
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double radDiffLat = Math.toRadians(lat2-lat1);
		double radDiffLon = Math.toRadians(lon2-lon1);
		
		double a = Math.sin(radDiffLat/2) * Math.sin(radDiffLat/2) +
				Math.cos(radLat1) * Math.cos(radLat2) *
				Math.sin(radDiffLon/2) * Math.sin(radDiffLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a),  Math.sqrt(1-a));
		
		return R * c;
	}
	
	double getEdgeLength(Edge e0){
		return calcDist(
			((Vertex)(adjList[e0.getStartV()].head.getVal())).lat,
			((Vertex)(adjList[e0.getStartV()].head.getVal())).lon,
			((Vertex)(adjList[e0.getEndV()].head.getVal())).lat,
			((Vertex)(adjList[e0.getEndV()].head.getVal())).lon
		);
	}
	
	double vToVDist(Vertex v0, Vertex v1){
		return calcDist(
				v0.lat,
				v0.lon,
				v1.lat,
				v1.lon
			);
	}
		
		
	public static void main(String args[]){
		
		
		
		
		if(args.length < 3){
			System.out.println("Please provide start and end vertices via the command line! <input file name> <start> <end>");
			System.exit(0);
		}
		try{
			DijkstraHighway d = new DijkstraHighway(args[0]);
			int start = d.getIndexOf(args[1]);
			int end = d.getIndexOf(args[2]);
			
			
			//d.printAdjList();
			d.dijkstra(start, end);
			
			System.out.println("end");
			int x = 0;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error! Please enter args as follows: <input file name> <start> <end>");
			System.exit(0);
		}
		

	}
	
}

