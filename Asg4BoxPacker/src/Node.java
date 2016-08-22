class Node<E>{
	
	private E value;
	private int key;
	
	Node(int k, E v){
		setKey(k);
		setValue(v);
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String toString(){
		return "K: " + key + "V: " + value;
	}
	
}