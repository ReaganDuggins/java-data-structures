public class Queue {
	
	public Node head;
	
	
	public void add(int p){
		if(head == null){
			head = new Node(p, null);
		}else{
			Node n = head;
			Node nn = head.next;
			while(nn != null){
				n = n.next;
				nn = nn.next;
			}
			nn = new Node(p, null);
			n.next = nn;
		}
	}
	
	public int remove(){
		//because -1 is the number that says the queue is empty it is unwise to put a -1 into the queue
		if(head == null){
			return -1;
		}
		if(head.next == null){
			int x = head.n;
			head = null;
			return x;
		}
		Node n = head;
		Node nn = head.next;
		while(nn.next != null){
			// go to end of queue (tail)
			n = n.next;
			nn = nn.next;
		}
		//remove final node
		n.next = null;
		return nn.n;
	}
	
	public boolean equals(Queue q){
		//returns true if both queues have the same numbers in the same order
		Node n0 = head;
		Node n1 = q.head;

		//these two ifs check for equality if either of the queues is empty
		if(n0 == null){
			if(n1 == null)
				return true;
			return false;
		}
		if(n1 == null){
			if(n0 == null)
				return true;
			return false;
		}
		
		while(n0 != null){
			
			if(n1 == null) return false;
			if(n0.n != n1.n) return false;
			
			n0 = n0.next;
			n1 = n1.next;
		}
		
		return true;
	}
	
	public int getTail(){
		Node n = head;
		while(n.next != null){
			n = n.next;
		}
		return n.n;
	}
	
	public int getLargest(){
		Node n = head;
		int x = 0;
		while (n != null){
			if(n.n > x){
				x = n.n;
			}
			n = n.next;
		}
		return x;
	}
	
	public boolean isEmpty() {
		if(head == null)
			return true;
		return false;
	}
	
	public void print(){
		Node n = head;
		while(n != null){
			System.out.print(n + "->");
			n = n.next;
		}
		System.out.print("|");
	}
	
	class Node{
		int n;
		Node next;
		
		public Node(int n, Node next){
			this.n = (int) n;
			this.next = next;
		}
		
		public String toString(){
			return n + "";
		}
	}
	
}
