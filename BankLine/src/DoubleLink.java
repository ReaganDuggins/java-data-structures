public class DoubleLink {
	
	Node head;
	Node tail;
	int length = 0;
	
	
	//adds a node to the list and updates length
	public void add(Node node){
		int num = node.pri;
		if(head == null){
			head = new Node(num);
			length++;
			setTail();
		}else if(num < head.n){
			//put before head
			Node nn = new Node(num, head, null);
			head = nn;
			length++;
			setTail();
			return;
		}else{
			//put after head
			if(head.next == null){
				//it becomes head.next
				head.next = new Node(num, null, head);
				length++;
				setTail();
			}else{
				Node fred = head;//fred likes to iterate through linked lists
				while(fred.next != null){
					if(num > fred.n && num < fred.next.n){
						//put new node between fred and fred.next
						Node nn = new Node(num, fred.next, fred);
						fred.next.prev = nn;
						fred.next = nn;
						length++;
						setTail();
						return;
					}
					fred = fred.next;
				}
				fred.next = new Node(num, null, fred);
				length++;
				setTail();
			}
		}
	}
	
	//deletes a node from the list and updates length
	public boolean remove(long n){
		Node fred = head;
		while(fred != null){
			if(fred.n == n){
				if(fred.prev != null){
					if(fred.next != null){
						fred.prev.next = fred.next;
						fred.next.prev = fred.prev;
						length--;
						return true;
					}else{
						fred.prev.next = null;
						length--;
						return true;
					}
				}else{
					if(fred.next == null){
						head = null;
						length--;
						return true;
					}else{
						head = fred.next;
						fred.next.prev = null;
						length--;
						return true;
					}
				}
			}
			fred = fred.next;
		}
		return false;
	}
	
	//sets tail
	public void setTail(){
		Node n = head;
		while(n.next!=null){n = n.next;}
		tail = n;
	}
	
	//prints the list forwards
	public void print(){
		if(head == null){
			System.out.println("Empty List");
			return;
		}
		for(Node fred = head; fred != null; fred = fred.next){
			System.out.println(fred);
		}
	}
	
	//prints the list backwards
	public void printBackwards(){
		if(head == null){
			System.out.println("Empty List");
			return;
		}
		Node fred = head;
		while(fred.next != null){
			fred = fred.next;
		}
		for(Node i = fred; i != null; i = i.prev){
			System.out.println(i);
		}
	}

	
	/**********************Node*************************/
	
	private class Node {
		Node next;
		Node prev;
		long n;
		int pri;
		
		public Node(int n){
			this.n = n;
		}
		
		public Node(int n, Node nex, Node pre){
			next = nex;
			prev = pre;
			this.n = n;
		}
		
		public String toString(){
			return "Num: " + n;
		}
	}
	
}
