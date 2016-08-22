public class Node<E> {
	private int key;
	private E val;
	public Node next;
	
	public Node(int key, E val){
		setKey(key);
		setVal(val);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public E getVal() {
		return val;
	}

	public void setVal(E val) {
		this.val = val;
	}

}
