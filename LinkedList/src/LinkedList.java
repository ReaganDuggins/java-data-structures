
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
			while(currentNode.next != null){
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
		String list = "::";
		if(isHeadless()){
			return list + "null";
		}

		for(Node currentNode = head; currentNode != null; currentNode = currentNode.next){
			list = list + currentNode.value + "->";
		}

		list += "null";
		return list;
	}
	
	/**********NODE***********/
	static class Node {
		
		int value;
		Node next;
		
		Node(int value) {
			this.value = value;
		}

		public String toString(){
			return "Num: " + value;
		}
	}
}
