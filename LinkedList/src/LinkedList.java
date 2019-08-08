
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

		Node(int num, Node next) {
			n = num;
			this.next = next;
		}
		
		public String toString(){
			String nex = "null";
			if(next != null){
				nex = Integer.toString(next.n);
			}
			return "Num: " + n;
		}
		
	}
	
	public static void main(String[] args){
		LinkedList ll = new LinkedList();
		System.out.println(ll);
		ll.add(2);
		System.out.println(ll);
		ll.add(3);
		System.out.println(ll);
	}
	
}
