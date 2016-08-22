import java.util.EmptyStackException;
public class LLStack<E> implements Stack<E> {
	
	Node bottom;
	Node top;
	
	public LLStack(){
		
	}
	
	public void push(E e) {
		if(bottom == null)
			bottom = top = new Node(e, null, null);
		else{
			if(bottom.next == null)
				bottom.next = top = new Node(e, null, bottom);
			else{
				top.next = new Node(e, null, top);
				top = top.next;
			}
		}
	}

	public E pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		else{
			if(top == bottom){
				E e = top.data;
				top = bottom = null;
				return e;
			}else{
				E e = top.data;
				top = top.prev;
				top.next = null;
				return e;
			}
		}
	}

	public boolean isEmpty() {
		if(bottom == null)
			return true;
		else
			return false;
	}
	
	public String toString(){
		String s = "Head:";
		for(Node n = bottom; n != null; n = n.next){
			s += "-" + n + "-";
		}
		return s.substring(0, s.length()-1);
	}
	
	
	class Node{
		
		E data;
		Node next;
		Node prev;
		
		public Node(E e, Node n, Node p){
			data = e;
			next = n;
			prev = p;
		}
		
		public String toString(){
			return data.toString();
		}
		
	}
	
}
