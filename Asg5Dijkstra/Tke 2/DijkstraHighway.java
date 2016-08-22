import java.io.*;
import java.util.Scanner;
class DijkstraHighway {
	
	AdjList al;
	Vertex[] settled;
	Vertex[] unsettled;
	double[] distance;
	String starts;
	String ends;
	int start;
	int end;
	int numV;
	int numE;
	final double INF = Double.MAX_VALUE;
	
	public DijkstraHighway(String inf, String s, String nd){
		al = new AdjList();
		starts = s;
		ends = nd;
		System.out.println("Reading inFile");
		readFile(inf);
	}
	
	
	
	public void readFile(String fname){
		//read the input file and initialize the adjList
		try{
			File f = new File(fname);
			Scanner scan = new Scanner(f);
			String line = scan.nextLine();
			numV = Integer.parseInt(line.split("\\s+")[0]);
			numE = Integer.parseInt(line.split("\\s+")[1]);
			distance = new double[numV];
			settled = new Vertex[numV];
			unsettled = new Vertex[numV];
			System.out.println(numV);
			while(scan.hasNextLine()){
				line = scan.nextLine();
				
				String[] parts = line.split(",*\\s+");
				if(parts.length <3) continue;
				try{
					//this tries to parse the first part of the line into an int, if it succeeds then this is an edge, the catch block will handle vertices
					int a = Integer.parseInt(parts[0]);
					int b = Integer.parseInt(parts[1]);
					String name = parts[2].trim();
					Edge e = new Edge(name, al.getVert(a), al.getVert(b));
					al.addEdge(e);
				}catch(NumberFormatException e){
					// this block handles vertices. These are verts because the first part is a string, not an int
					String name = parts[0].trim();
					double x = Double.parseDouble(parts[1]); // the latitude
					double y = Double.parseDouble(parts[2]); // the longitude
					Vertex v = new Vertex(name, x, y);
					al.addVert(v);
				}
				
			}
		}catch(FileNotFoundException e){
			System.out.print("Could not read file!");
		}
		start = al.getVertInd(starts);
		end = al.getVertInd(ends);
		prep();
	}
	
	
	
	public void prep(){
		// this sets up for running the pathfinding algorithm
		AdjList.HeadNode hn = al.head;
		int i = 0;
		// set distances to infinity
		for(i = 0; i < distance.length; i++){
			distance[i] = INF;
		}
		if(start == -1){
			System.out.println("Error! There is no Vertex: " + starts + "!");
			System.exit(0);
		}
		distance[start] = 0;
		settle(al.getVert(start));
		evaluateNeighbors(al.getVertNode(al.getVert(start)));
		calc();
	}
	
	public void calc(){
		// do the actual dijkstra calculations
		Vertex prev = al.getVert(start);
		while(!unsettledEmpty()){
			AdjList.HeadNode evalNode = al.getVertNode(getLowestDistVert());
			settle(evalNode.vert); 
			evaluateNeighbors(evalNode);
		}
		printPath();
	}
	
	public String printPath(){
		// print the path from start to end
		//System.out.println("-----\n" + al + "\n-----");
		String p = "";
		String vp = "";
		String ep = "";
		Vertex curv = al.getVert(end);
		//Vertex[] verts = new Vertex[numV];
		//Edge[] ees = new Edge[numV-1];
		int i = 0;
		while(curv != null){
			
			//verts[i] = curv;
			//ees[i] = curv.pree;
			
			String cpn = "";
			if(curv.pree != null) cpn = curv.pree.name;
			p = curv.name + "(" + cpn + ")" + " -> " + p;
			vp = vp + curv.name + " " + curv.lat + ", " + curv.lon + "\n";
			System.out.println(cpn);
			if(curv.prev != null) ep = ep + i + " " + (i+1) + " " + al.getEdge(curv.name, curv.prev.name).name + "\n";
			curv = curv.prev;
			i++;
		}
		System.out.println("\n" + p);
		writePath(vp, ep);
		return p;
	}
	
	public void writePath(String vp, String ep){
		// writes the path to a file
		/*String s = "";
		// have to go bottom-to-top
		for(Edge e : ees){
			if(e == null) break;
			s = "\n" + al.getVertInd(e.v0.name) + " " + al.getVertInd(e.v1.name) +  " " + e.name + s;
		}
		// get all vertices
		AdjList.HeadNode hn = al.head;
		Vertex finVert = null;
		int lastV = -1;
		while(hn != null){
			if(hn.next == null) finVert = hn.vert;
			Vertex v = hn.vert;
			s = "\n" + v.name + " " + v.lat + ", " + v.lon + s;
			hn = hn.next;
			lastV++;
		}
		
		// find the index of the last vert and edge

		int lastE = -1;
		for(int i = 0; i < ees.length; i++){
			if(ees[i] == null) break;
			lastE = i;
		}
		s = (lastV+1) + " " + (lastE+1) + s; // now s just needs to be written to file
		*/
		try{
			String nam = starts + "--" + ends + ".gra";
			nam = String.join("-", nam.split("[\\/]"));
			int verts = vp.split("\n").length;
			int ees = ep.split("\n").length;
			PrintWriter writer = new PrintWriter(nam, "UTF-8");
			writer.println(verts + " " + ees + "\n" + vp + ep);
			writer.close();
		}catch(IOException e){
			System.out.println("Could not write to file!" );
			e.printStackTrace();
			
		}
		
	}
	
	
	public void evaluateNeighbors(AdjList.HeadNode hn){
		// evaluates the distances from start of this node's unsettled neighbors
		AdjList.Node n = hn.right;
		while(n != null){
			// go through each edge attached to this node
			if(!isSettled(n.e.v1)){
				// only evaluate on unsettled nodes
				double d = n.e.dist;
				double newd = d + distance[al.getVertInd(hn.vert.name)];
				
				if(distance[al.getVertInd(n.e.v1.name)] > newd){
					distance[al.getVertInd(n.e.v1.name)] = newd;
					al.getVert(al.getVertInd(n.e.v1.name)).prev = hn.vert;
					al.getVert(al.getVertInd(n.e.v1.name)).pree = n.e;
					unsettle(n.e.v1);
				}
				
			}
			n = n.next;
		}
		
	}
	
	public boolean isSettled(Vertex v){
		// true if v is in settled
		for(int i = 0; i < settled.length; i++){
			if(settled[i] != null && v.name.equals(settled[i].name)) return true;
			
		}
		return false;
	}
	
	public boolean unsettledEmpty(){
		// true if unsettled is empty
		for(int i = 0; i < unsettled.length; i++){
			if(unsettled[i] != null) return false;
		}
		return true;
	}
	
	public Vertex getLowestDistVert(){
		// returns the vertex from unsettled with the shortest distance
		
		double dist = INF;
		int ind = -1;
		
		for(int i = 0; i < distance.length; i++){
			if(unsettled[i] != null && distance[i] < dist){
				dist = distance[i];
				ind = i;
			}
		}
		return al.getVert(ind);
	}
	
	public void settle(Vertex v){
		// settle a vert
		int i = al.getVertInd(v.name);
		settled[i] = v;
		unsettled[i] = null;
	}
	
	public void unsettle(Vertex v){
		int i = al.getVertInd(v.name);
		unsettled[i] = v;
		settled[i] = null;
	}
	
	
	public static void main(String[] args){
		if(args.length < 3){
			System.out.println("Arguments should be: <input file name> <start point name> <end point name>");
			System.exit(0);
		}
		try{
			DijkstraHighway d = new DijkstraHighway(args[0], args[1], args[2]);
			d.al = new AdjList();
			
			/**d.al.addVert(new Vertex("S",0.0,0.0));     //0
			d.al.addVert(new Vertex("n",0.0,1.0));     //1
			d.al.addVert(new Vertex("s",0.0,-1.0));    //2
			d.al.addVert(new Vertex("w",-1.0,0.0));    //3
			d.al.addVert(new Vertex("e",1.0,0.0));     //4
			d.al.addVert(new Vertex("nw",-1.0,1.5));   //5
			d.al.addVert(new Vertex("se",1.0,-1.0));   //6
			d.al.addVert(new Vertex("X",-0.5,0.3));     //7
			
				
			d.al.addEdge(new Edge("S/n", d.al.getVert(0), d.al.getVert(1)));
			d.al.addEdge(new Edge("S/s", d.al.getVert(0), d.al.getVert(2)));
			d.al.addEdge(new Edge("S/w", d.al.getVert(0), d.al.getVert(3)));
			d.al.addEdge(new Edge("S/e", d.al.getVert(0), d.al.getVert(4)));
			
			d.al.addEdge(new Edge("X/n", d.al.getVert(7), d.al.getVert(1)));
			d.al.addEdge(new Edge("X/s", d.al.getVert(7), d.al.getVert(2)));
			d.al.addEdge(new Edge("X/w", d.al.getVert(7), d.al.getVert(3)));
			d.al.addEdge(new Edge("X/e", d.al.getVert(7), d.al.getVert(4)));
			
			d.al.addEdge(new Edge("n/e", d.al.getVert(1), d.al.getVert(3)));
			d.al.addEdge(new Edge("n/w", d.al.getVert(1), d.al.getVert(4)));
			d.al.addEdge(new Edge("s/e", d.al.getVert(2), d.al.getVert(3)));
			d.al.addEdge(new Edge("s/w", d.al.getVert(2), d.al.getVert(4)));
			
			d.al.addEdge(new Edge("e/se", d.al.getVert(4), d.al.getVert(6)));
			d.al.addEdge(new Edge("s/se", d.al.getVert(2), d.al.getVert(6)));
			d.al.addEdge(new Edge("n/nw", d.al.getVert(1), d.al.getVert(5)));
			d.al.addEdge(new Edge("w/nw", d.al.getVert(3), d.al.getVert(5)));
				
			d.al.addEdge(new Edge("nw/X", d.al.getVert(5), d.al.getVert(7)));
			d.al.addEdge(new Edge("se/X", d.al.getVert(6), d.al.getVert(7)));
			**/
			//d.al.addEdge(new Edge("S/X", d.al.getVert(0), d.al.getVert(7)));
			
			
			
			//System.out.println(d.al);
			
			
			//for(int i = 0; i < d.unsettled.length; i++){
			//	System.out.println(d.settled[i] + "  |  " + d.unsettled[i] + "  |  " + d.distance[i]);
			//}
		}catch(NumberFormatException e){
			System.out.println("Start and end point must be integers!");
		}
	}
	
}