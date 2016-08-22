/*
 * Author: John Hunt
 * This class is a binary heap implemented as a tree
 * it provides a method to build the tree, print the 
 * tree and to remove the last child from the tree.
 * The primary operations of remove and insert are left
 * as exercises
 */

public class Heap {

	class Node {
		int num;
		Node left;
		Node right;
		
		Node(int num, Node left, Node right){
			this.num = num;
			this.left = left;
			this.right = right;
		}
		
		public String toString(){
			return num + "";
		}
	}
	
	Node root;
	int nodeCnt;
	
	Heap(){
		root = null;
		nodeCnt = 0;
	}
	
	/*
	 * Builds a complete binary tree from the array
	 * passed in.  This method does not attempt to
	 * order the nodes on value.  For the resulting tree
	 * to have the quality of heapness the array must 
	 * be in a correct order.
	 */
	void build(int nums[]){
		Node nodes[] = new Node[nums.length];
		for(int i = 0; i < nums.length; i++){
			nodes[i] = new Node(nums[i], null, null);
		}
		root = nodes[0];
		for(int i=0; i < nums.length/2; i++){
			nodes[i].left = nodes[2*i+1];
			nodes[i].right = nodes[2*i+2];
		}
		nodeCnt = nums.length;
	}
	
	void print(Node c, String indent){
		if(c != null) {
			System.out.println(indent + c.num);
			print(c.left, indent + "    ");
			print(c.right, indent + "    ");
		}
	}
	
	/* 
	 * Performs a pre-order traversal of the tree
	 * printing the data value
	 */
	void printHeap() {
		print(root, "");
	}
	
	/*
	 * Returns the number of nodes in the tree
	 */
	int size() {
		return nodeCnt;
	}
	
	/*
	 * Removes the last child in the tree 
	 * returning the last child's value
	 * The nodeCnt is decremented
	 */
	int removeLastChild() {
		int cnt = nodeCnt;
		String path = "";
		while(cnt >= 1) {
			path = (cnt %2) + path;
			cnt = cnt / 2;
		}

		int value = -1;
		Node c = root;
		for(int i = 1; i < path.length()-1; i++){
			if(path.charAt(i)== '0') {
				c = c.left;
			} else {
				c = c.right;
			}
		}
		if(path.length() == 1) {
			value = root.num;
			root = null;
		} else if(path.charAt(path.length()-1)== '0') {
			value = c.left.num;
			c.left = null;
		} else {
			value = c.right.num;
			c.right = null;
		}
		nodeCnt--;
		return value;
	}
	
	public int remove(){
		if(root == null){
			System.out.println("Empty Heap! Returning -1");
			return -1;
		}
		int rt = root.num;
		int lc = removeLastChild();
		if(root == null){
			// this means that removeLastChild removed the root, which means that this is the last value, so no bubbling is necessary.
			return lc;
		}
		root.num = lc;
		Node n = root;
		
		boolean lefBig;
		boolean rigBig;
		
		while(n.left != null || n.right != null){
			
			lefBig = false;
			rigBig = false;
			
			if(n.left == null && n.right == null){
				return rt;
			}else if((n.left != null && n.right == null) || n.left.num > n.right.num){
				lefBig = true;
			}else if((n.right != null && n.left == null) || n.right.num > n.left.num){
				rigBig = true;
			}
			
			if(lefBig && n.left.num > n.num){
				// bubble left
				int tmp = n.num;
				n.num = n.left.num;
				n.left.num = tmp;
				n = n.left;
				
			}else if(rigBig && n.right.num > n.num){
				// bubble right
				int tmp = n.num;
				n.num = n.right.num;
				n.right.num = tmp;
				n = n.right;
				
			}else{
				// return te root value
				return rt;
			}
			
			
		}
		return rt;
	}
	
	public static void main(String[] args) {
		Heap h = new Heap();
		int numbers[] = {50, 30, 20, 25, 15, 7, 18, 22, 13, 14, 9, 2,3, 1,17};
		h.build(numbers);
		h.printHeap();
		//System.out.println(h.remove());
		// put your code to test remove here
		
		while(h.size() > 0) {
		    System.out.println(h.size() + "--" + h.remove());
		}
		System.out.println("Fin");

	}

}