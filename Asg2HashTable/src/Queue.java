public class Queue {
	
	public Node head;
	
	
	public void add(String p){
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
	
	public String remove(){
		if(head == null){
			return null;
		}else{
			Node n = head;
			head = n.next;
			n.next = null;
			return n.p;
		}
	}
	

	

	
	public String getTail(){
		Node n = head;
		while(n.next != null){
			n = n.next;
		}
		return n.p;
	}
	
	public boolean isEmpty() {
		if(head == null)
			return true;
		return false;
	}
	
	public void print(){
		Node n = head;
		while(n != null){
			System.out.print(n + "\n");
			n = n.next;
		}
		System.out.print("|");
	}
	
	class Node{
		String p;
		Node next;
		
		public Node(String n, Node next){
			this.p = n;
			this.next = next;
		}
		
		public String toString(){
			return p + "";
		}
	}
	
	
}