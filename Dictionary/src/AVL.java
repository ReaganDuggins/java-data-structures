

public class AVL extends BST{
	
	/*******
	 * Now just make the method that does the balancing. All the pieces are in place.
	 */
	
	public void add(String word, String def){
		word = word.toLowerCase();
		//go to lowercase
		if(root == null){
			root = new Node(word, def);
			root.balance = 0;
			return;
		}
		Node n = root;
		while(true){
			if(n.word.equals(word)){
				if(!n.hasDef(def)){
					//if the word already exists check if adding a new definition
					n.addDef(def);
					return;
				}else if(n.hasDef(def)){
					return;
				}
			}
			if((n.word.compareTo(word)) > 0){
				//go or insert left
				if(n.left == null){
					n.left = new Node(word, def);
					n.left.up = n;
					n.left.balance = 0;
					n.left.updateBalanceFactors();
					n.left.avlBalance();
					return;
				}
				n = n.left;
			}else{
				//go or insert right
				if(n.right == null){
					n.right = new Node(word, def);
					n.right.up = n;
					n.right.balance = 0;
					n.right.updateBalanceFactors();
					n.right.avlBalance();
					return;
				}
				n = n.right;
			}
		}
	}
	
	
	
	
}
