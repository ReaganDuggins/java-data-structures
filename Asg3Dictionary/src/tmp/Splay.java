public class Splay extends BST{	
	
	public void add(String word, String def){
		word = word.toLowerCase();
		//go to lowercase
		if(root == null){
			root = new Node(word, def);
			return;
		}
		Node n = root;
		while(true){
			if(n.word.equals(word)){
				if(!n.hasDef(def)){
					//if the word already exists check if adding a new definition
					n.addDef(def);
					n.splay();
					return;
				}else if(n.hasDef(def)){
					n.splay();
					return;
				}
			}
			if((n.word.compareTo(word)) > 0){
				if(n.left == null){
					n.left = new Node(word, def);
					n.left.up = n;
					n.left.splay();
					return;
				}
				n = n.left;
			}else{
				//go or insert right
				if(n.right == null){
					n.right = new Node(word, def);
					n.right.up = n;
					n.right.splay();
					return;
				}
				n = n.right;
			}
		}
	}
	
	
	
}
