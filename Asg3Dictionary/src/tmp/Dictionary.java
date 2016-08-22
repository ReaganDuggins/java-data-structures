public class Dictionary{
	public static void main(String args[]){
		if(args.length < 1){
			System.out.println("Need at least 1 arg!");
			return;
		}
		if(args[0].toLowerCase().equals("bst")){
			BST bst = new BST();
			bst.treeType = "bst";
			for(int i = 1; i < args.length; i++){
				bst.load(args[i]);
			}
			bst.prompt();
		}else if(args[0].toLowerCase().equals("avl")){
				System.out.println("Does not support avl");
		}else if(args[0].toLowerCase().equals("splay")){
			Splay s = new Splay();
			s.treeType = "splay";
			for(int i = 1; i < args.length; i++){
				s.load(args[i]);
			}
			s.prompt();
		}else{
			System.out.println(args[0] + " is not a known tree type.");
		}
	}
}