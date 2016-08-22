
public class Link {
	Node head;
	Link(){
		
	}
	
	public void addNode(Node n){
		if(head == null){
			head = n;
		}
		else{
			Node nn = head;
			while(nn.next != null){
				nn = nn.next;
			}
			nn.next = new Node(n.getKey(),n.getVal());
		}
	}
	
	
	public String toString(){
		if(head == null){
			return "Empty List";
		}
		Node n = head;
		String s = "";
		while(n != null){
			s += n.getVal() + " | ";
			n = n.next;
		}
		return s;
	}
}
