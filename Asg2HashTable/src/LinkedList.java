
public class LinkedList {

	public Node head;
	public int length;
	
	public LinkedList(){
		
	}
	
	public PairClass getByValue(Object value){
		Node n = head;
		while(n != null){
			if(value.equals(n.p.f))
				return n.p;
			n = n.next;
		}
		return null;
	}
	
	public void add(PairClass p){
		if(head == null){
			head = new Node(p, null);
			length++;
		}else{
			Node n = head;
			while(n.next != null){
				n = n.next;
			}
			n.next = new Node(p, null);
			length++;
		}
	}
	
	public void print(){
		Node n = head;
		while(n != null){
			System.out.print(n + "->");
			n = n.next;
		}
		System.out.print("|");
	}
	
	public PairClass[] returnAllPairs(){
		Node n = head;
		PairClass[] pairs = new PairClass[length];
		int i = 0;
		while(n != null){
			pairs[i] = n.p;
			i++;
			n = n.next;
		}
		return pairs;
	}
	
	public void setValue(Object key, Object value){
		//this method allows the setting of the value in a pair, but only works if there is a working .equals function for the data type
		Node n = head;
		while(n != null){
			if(n.p.f.equals(key)){
				n.p.s = value;
				return;
			}
			n = n.next;
		}
	}
	
	public boolean hasKey(Object key){
		Node n = head;
		if(n == null) return false;
		while(n != null){
			if(n.p.f.equals(key)) return true;
			n = n.next;
		}
		return false;
	}
	
	
	
	class Node{
		PairClass p;
		Node next;
		
		public Node(PairClass p, Node next){
			this.p = (PairClass) p;
			this.next = next;
		}
		
		public String toString(){
			return p.toString();
		}
		
		public void setValue(Object value){
			p.s = value;
		}
	}

	public int numberContained() {
		//counts the number of values in the list, only works if the value is an int
		int total = 0;
		Node n = head;
		while(n != null){
			total += (Integer)n.p.s;
			n = n.next;
		}
		return 0;
	}
	
	public static void main(String[] args){
		LinkedList l = new LinkedList();
		l.add(new PairClass("blurf","blee"));
		l.add(new PairClass("jim","jeff"));
		System.out.println(l.getByValue("blee") + "::");
		l.print();
	}
}
