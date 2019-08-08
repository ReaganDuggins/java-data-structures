
public class LinkedList {
	
	Node head;
	
	public LinkedList(){}
	
	public LinkedList(int value){
		head = new Node(value);
	}
	
	public void add(int value){
		if(isHeadless()){
			head = new Node(value);
			System.out.println("Head Added\n" + head);
		}else{
			Node currentNode = head;
			while(currentNode.hasNext()){
				currentNode = currentNode.next;
			}
			currentNode.next = new Node(value);
			System.out.println("Added\n" + currentNode);
		}
	}

	private boolean isHeadless() {
		return head == null;
	}

	public String toString(){
		if(isHeadless()){
			return "empty list";
		}

		return "HEAD" + head.toString();
	}
	
	/**********NODE***********/
	static class Node {
		
		int value;
		Node next;
		
		Node(int value) {
			this.value = value;
		}

		public String toString(){
			String thisNode = "(" + value + ")";
			String nextNode = hasNext()
					? "->" + next.toString()
					: "";
			return thisNode + nextNode;
		}

		public boolean hasNext() {
			return next != null;
		}
	}
}
