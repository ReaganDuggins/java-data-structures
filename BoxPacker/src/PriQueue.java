

public class PriQueue<E> implements PriQue{
	
	Node head;//front
	Node tail;//back
	int length;
	
	public void insert(int pri, Object data) {
		E dat = (E) data;
		if(head == null){
			head = tail = new Node(pri, dat, dat.getClass().toString(), null, null);
			length++;
		}else{
			//head != null
			if(pri > head.pri){
				//new node goes before head
				Node nn = new Node(pri, dat, dat.getClass().toString(), head, null);
				head = nn;
				if(length == 2)//set tail if length is 2
					tail = nn.next;
			}else{
				Node iter = head;
				while(iter.next != null && iter.next.pri > pri){
					iter = iter.next;
				}
				if(iter.next == null){
					iter.next = new Node(pri, dat, dat.getClass().toString(), null, iter);
					tail = iter.next;
				}else{
					Node nn = new Node(pri, dat, dat.getClass().toString(), iter.next, iter);
					iter.next.prev = nn;
					iter.next = nn;
				}
			}
			
		}
	}
	
	public E remove() {
		if(isEmpty()){
			System.out.println("Queue Empty");
			return null;
		}
		Node n = head;
		if(head.next == null){
			head = null;
			return n.obj;
		}
		head = head.next;
		head.prev = null;
		return n.obj;
	}

	public boolean isEmpty() {
		if(head == null)
			return true;
		return false;
	}
	
	public E peek(){
		if(isEmpty()) return null;
		return head.obj;
	}
	
	public void print(){
		if(isEmpty()){
			System.out.println("This queue is empty!");
			return;
		}
		for(Node n = head; n != null; n = n.next){
			System.out.print(n.obj.toString() + " ");
		}
		System.out.println();
	}

	
/**************Node*******************/
	private class Node {
		
		Node next;
		Node prev;
		E obj;
		int pri;
		String dtype;
		
		public Node(int pri, E e, String dt){
			obj = e;
			this.pri = pri;
			dtype = dt;
		}
		
		public Node(int pri, E e, String dt, Node next, Node prev){
			this.next = next;
			this.prev = prev;
			this.pri = pri;
			dtype = dt;
			obj = e;
		}

		public String toString(){
			return pri + ": " + obj.toString();
		}
		
		
	}
	
	public static void main(String[] args){
		
		PriQueue q = new PriQueue();
		q.insert(19, "19");
		q.insert(14, "14");
		q.insert(6, "6");
		q.insert(10, "10");
		q.insert(30, "30");
		q.insert(13, "13");
		q.insert(26, "26");
		q.insert(5, "5");
		q.insert(4, "4");
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println("Fin");
		
	}

}