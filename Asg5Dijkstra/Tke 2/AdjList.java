public class AdjList{
	
	HeadNode head;
	
	
	public HeadNode getVertNode(Vertex v){
		// returns the node containing the vertex
		HeadNode hn = head;
		while(hn != null){
			if(v.name.equals(hn.vert.name)) return hn;
			hn = hn.next;
		}
		return null;
	}
	
	public Edge getEdge(String n0, String n1){
		System.out.println(n0 + ":::" + n1);
		HeadNode h = head;
		while(h != null){
			Node n = h.right;
			while(n != null){
				if((n.e.v0.name.equals(n0) && n.e.v1.name.equals(n1)) ||(n.e.v0.name.equals(n1) && n.e.v1.name.equals(n0))){
					// then it's a match
					return n.e;
				}
				n = n.next;
			}
			h = h.next;
		}
		return null;
	}
	
	public Vertex getVert(int x){
		//returns the xth vertex
		////0 indexed
		int i = 0;
		HeadNode hn = head;
		while(hn != null){
			if(i == x){
				// this is the vertex so return it
				return hn.vert;
			}
			i++;
			hn = hn.next;
		}
		return null;
	}
	
	public int getVertInd(String name){
		//returns the index of vertex 'name'
		////0 indexed
		int i = 0;
		HeadNode hn = head;
		while(hn != null){
			if(name.equals(hn.vert.name)){
				// this is the vertex so return it
				return i;
			}
			i++;
			hn = hn.next;
		}
		return -1;
	}
	
	public void addVert(Vertex v){
		// adds a vertex to the bottom of the adjlist
		if(head == null){
			// if it's the first one add it to head
			head = new HeadNode(null, v);
			return;
		}
		
		// otherwise add it to the end
		HeadNode hn = head;
		while(hn.next != null){
			hn = hn.next;
		}
		
		hn.next = new HeadNode(null, v);
		
	}
	
	public void addEdge(Edge e){
		int i = 0;
		HeadNode hn = head;
		
		while(hn != null){
			
			if(e.v0.name.equals(hn.vert.name)){
				// this vertex is the first vertex in e (as opposed to the second one)
				if(hn.right == null){
					// it's the first edge for this vertex
					hn.right = new Node(e);
				}else{
					// it isn't the first edge for this vertex
					Node h = hn.right;
					while(h.next != null){
						h = h.next;
					}
					h.next = new Node(e);
				}
			}else if(e.v1.name.equals(hn.vert.name)){
				// this is the second vertex in e
				if(hn.right == null){
					// it's the first edge for this vertex
					// and then switch the order of the vertices so that this vertex will be v0
					hn.right = new Node(new Edge(e.name, e.v1, e.v0));
				}else{
					// it isn't the first edge for this vertex
					Node h = hn.right;
					while(h.next != null){
						h = h.next;
					}
					h.next = new Node(new Edge(e.name, e.v1, e.v0));
				}
			}
			
			i++;
			hn = hn.next;
		}
	}
	
	public String toString(){
		HeadNode hn = head;
		String s = "";
		while(hn != null){
			
			s += hn.toString() + "\n";
			hn = hn.next;
		}
		
		return s;
	}
	
	////////////////Classes////////////////
	
	class HeadNode {
		HeadNode next; // the next HeadNode
		Node right; // this HeadNode's list of Edge connections
		Vertex vert;
		
		HeadNode(Node r, Vertex v){
			right = r;
			vert = v;
		}
		
		public String toString(){
			String s = "";
			
			Node r = right;
			while(r != null){
				s += r.e + " | ";
				r = r.next;
			}
			
			return vert.toString() + ": " + s;
		}
	}
	
	class Node {
		Node next;
		Edge e;
		
		Node(Edge e){
			this.e = e;
		}
		
		public String toString(){
			return e.toString();
		}
		
	}
	
	
	/*public static void main(String[] args){
		Vertex v = new Vertex("Blurf", 31.24683, -22.42893);
		Vertex vv = new Vertex("Blee", 2.7, 7.2);
		Vertex vvv = new Vertex("Blarg", 0.0, 0.0);
		Edge e = new Edge("Blurf/Blee", v, vv);
		Edge ee = new Edge("Blurf/Blarg", v, vvv);
		AdjList a = new AdjList();
		a.addVert(v);
		a.addVert(vv);
		a.addVert(vvv);
		a.addEdge(e);
		a.addEdge(ee);
		System.out.println(a);
		
	}*/
	
}