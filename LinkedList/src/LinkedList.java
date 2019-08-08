
public class LinkedList {
	
	Node head;
	
	public LinkedList(){}
	
	public LinkedList(int n){
		head = new Node(n);
	}
	
	public void add(int n){
		if(head == null){
			head = new Node(n);
			System.out.println("Head Added\n" + head);
		}else{
			Node nex = head;
			while(nex.next != null){nex = nex.next;}
			nex.next = new Node(n);
			System.out.println("Added\n" + nex);
		}
	}
	
	public String toString(){
		String s = "::";
		if(head == null){
			return s + "null";
		}

		for(Node n = head; n != null; n = n.next){
			s = s + n.n + "->";
		}

		s += "null";
		return s;
	}
	
	/**********NODE***********/
	static class Node {
		
		int n;
		Node next;
		
		Node(int num) {
			n = num;
		}

		public String toString(){
			return "Num: " + n;
		}
	}
}
