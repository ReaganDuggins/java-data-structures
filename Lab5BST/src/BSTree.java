
public class BSTree {
    static int startValues[] = {50, 20, 45, 55, 75, 12, 97, 17, 83, 76, 34, 66, 27, 53, 48};
    private class Node {
    	int key;
    	Node left;
    	Node right;
    	Node(int key){
    		this.key = key;
    		left = right = null;
    	}
    }
    Node root;
	public BSTree(){
		for(int i = 0; i < startValues.length; i++){
			insert(startValues[i]);
		}
	}
	public void insert(int key){
		// assumes no duplicates
		if(root == null){
			root = new Node(key);
		} else {
			Node c = root;
			while(true){
				
				if(key < c.key){
					// go left
					if(c.left == null) {
						c.left = new Node(key);
						break;
					} else {
						c = c.left;
					}
				} else {
					// go right
					if(c.right == null){
						c.right = new Node(key);
						break;
					} else {
						c = c.right;
					}
				}
			}
		}
	}
	
	
	public void preOrder(){
		preOrderGo(root, 0);
	}
	
	public void preOrderGo(Node n, int dots){
		if(n == null)
			return;
		for(int i = 0; i < dots; i++){
			System.out.print("...");
		}
		System.out.println(n.key);
		preOrderGo(n.left, dots + 1);
		preOrderGo(n.right, dots + 1);
	}
	
	public void postOrder(){
		postOrderGo(root, 0);
	}
	
	public void postOrderGo(Node n, int dots){
		if(n == null)
			return;
		for(int i = 0; i <= dots; i++){
			System.out.print("...");
		}
		System.out.println(n.key);
		postOrderGo(n.left, dots + 1);
		postOrderGo(n.right, dots + 1);
	}
	
	public void inOrder(){
		inOrderGo(root, 0);
	}
	
	public void inOrderGo(Node n, int dots){
		if(n == null)
			return;
		for(int i = 0; i <= dots; i++){
			System.out.print("...");
		}
		System.out.println(n.key);
		inOrderGo(n.left, dots + 1);
		inOrderGo(n.right, dots + 1);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		BSTree bst = new BSTree();
		System.out.println("------------Pre----------------");
		bst.preOrder();
		System.out.println("--------------Post--------------");
		bst.postOrder();
		System.out.println("---------------In-------------");
		bst.inOrder();
		
	}

}