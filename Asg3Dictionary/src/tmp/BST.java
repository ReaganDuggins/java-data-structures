import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class BST {
	
	Node root;
	String treeType;
	public static int blurf = 0;
	
	
	// change "throws" statement to actual error handling
	public void load(String fileName){
		try{
			Scanner scan = new Scanner(new File(fileName));
			String line = "";
			String[] words;
			while(scan.hasNextLine()){
				line = scan.nextLine();
				words = line.split("\\s+");
				if(words[0].toLowerCase().equals("add")){
					//if the command is add add the word and def
					try{
						String def = "";
						for(int i = 2; i < words.length; i++){
							def += words[i] + " ";
						}
						add(words[1], def.substring(0,def.length()-1) + ";");
						fix();
					}catch(ArrayIndexOutOfBoundsException e){
						System.out.println("Error for: \"" + line + "\"");
						e.printStackTrace();
					}
				}else if(words[0].toLowerCase().equals("quit")){
					puts("Done");
					System.exit(0);
				}else if(words[0].toLowerCase().equals("find")){
					puts(find(words[1]));
				}else if(words[0].toLowerCase().equals("list")){
					if(words.length > 2){
						list(words[1].toLowerCase(),words[2].toLowerCase());
					}else{
						list();
					}
				}else if(words[0].toLowerCase().equals("tree")){
					tree();
				}
				puts("");
				
			}
			scan.close();
		}catch(IOException e){
			//System.out.println("Error reading file: " + fileName);
			e.printStackTrace();
		}
	}
	
	public void prompt(){
		Scanner scan = new Scanner(System.in);
		String line = "";
		String[] words;
		while(true){
			System.out.println("Enter Command ('quit' to quit): ");
			line = scan.nextLine();
			System.out.println("\n");
			words = line.split("\\s+");
			if(words[0].toLowerCase().equals("add")){
				//if the command is add add the word and def
				try{
					String def = "";
					for(int i = 2; i < words.length; i++){
						def += words[i] + " ";
					}
					add(words[1], def.substring(0,def.length()-1) + ";");
					fix();
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Error for: \"" + line + "\"");
					e.printStackTrace();
				}
			}else if(words[0].toLowerCase().equals("quit")){
				puts("Done");
				scan.close();
				System.exit(0);
			}else if(words[0].toLowerCase().equals("find")){
				puts(find(words[1]));
			}else if(words[0].toLowerCase().equals("list")){
				if(words.length > 2){
					list(words[1].toLowerCase(),words[2].toLowerCase());
				}else{
					list();
				}
			}else if(words[0].toLowerCase().equals("tree")){
				tree();
			}
			puts("");
		}
	}
	
	
	
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
					return;
				}else if(n.hasDef(def)){
					return;
				}
			}
			if((n.word.compareTo(word)) > 0){
				if(n.left == null){
					n.left = new Node(word, def);
					n.left.up = n;
					return;
				}
				n = n.left;
			}else{
				//go or insert right
				if(n.right == null){
					n.right = new Node(word, def);
					n.right.up = n;
					return;
				}
				n = n.right;
			}
		}
	}
	
	
	
	public String find(String word){
		if(root.word.equals(word)){
			return root.toString();
		}
		
		Node n = root;
		while(n != null){
			if(n.word.equals(word)){
				if("splay".equals(treeType)){
					// if it is a splay tree then splay the found word
					n.splay();
				}
				return n.toString();
			}
			
			if(word.compareTo(n.word) < 0){
				//if need to go left go left
				n = n.left;
			}else{
				n = n.right;
			}
		}
		
		return "Word not found.";
	}
	
	public void list(){
		inOrder();
	}
	
	public void list(String startWord, String endWord){
		specialInOrder(root, "", startWord, endWord);
	}
	
	public void tree(){
		inOrder(root, "","");
	}
	
	public void fix(){
		fixInOrder(root);
	}
	
	public void quit(){
		System.exit(0);
	}
	
	/////////Housekeeping//////////
	
	public void inOrder(Node n, String dots, String side){
		if(n == null)
			return;
		System.out.print(dots);
		System.out.println(side + n.toString());
		inOrder(n.left, dots + "...", "[L]");
		inOrder(n.right, dots + "...", "[R]");
	}
	
	public void fixInOrder(Node n){
		if(n == null)
			return;
		if(n.left != null) n.left.up = n;
		if(n.right != null) n.right.up = n;
		fixInOrder(n.left);
		fixInOrder(n.right);
	}
	
	public void inOrder(){
		inOrderGo(root, "");
	}
	
	
	public void inOrderGo(Node n, String dots){
		if(n == null)
			return;
		
		inOrderGo(n.left, dots + "...");
		System.out.print(dots);
		System.out.println(n);
		inOrderGo(n.right, dots + "...");
	}
	
	public void specialInOrder(Node n, String dots, String start, String stop){
		if(n == null)
			return;
		specialInOrder(n.left, dots + "...", start, stop);
		if(n.word.compareTo(start) >= 0 && n.word.compareTo(stop) <= 0) System.out.println(dots + n.toString());
		specialInOrder(n.right, dots + "...", start, stop);
	}
	
	public Node makeNode(String word, String def){
		return new Node(word,def);
	}
	
	
	////////////////////////Node////////////////////////////
	class Node{
		//for alphabetical order, z is farthest to the right, a is farthest to the left
		String word;
		String defs;
		Node left;
		Node right;
		Node up;
		////These are so that other trees can simply inherit these methods
		//for versatile nodes
		boolean red;
		int balance = 999;
		
		public Node(String word, String def, Node left, Node right){
			this.word = word;
			defs = def;
			this.left = left;
			this.right = right;
		}
		
		//for red black
		public Node(String word, String def, boolean red, Node left, Node right){
			this.word = word;
			defs = def;
			this.left = left;
			this.right = right;
			this.red = red;
		}
		
		//for avl
		public Node(String word, String def, int balanceFactor, Node left, Node right){
			this.word = word;
			defs = def;
			this.left = left;
			this.right = right;
			this.balance = balanceFactor;
		}
		
		public Node(String word, String def){
			this.word = word;
			defs = def;
		}
		
		//for red black
		public Node(String word, String def, boolean red){
			this.word = word;
			defs = def;
			this.red = red;
		}
		
		//for avl
		public Node(String word, String def, int balanceFactor){
			this.word = word;
			defs = def;
			this.balance = balanceFactor;
		}
		
		public boolean hasDef(String d){
			if(defs.matches(".*"+d+";.*")){
				return true;
			}
			return false;
		}
		
		public void addDef(String d){
			if(hasDef(d)){
				return;
			}
			defs += d + "; ";
		}
		
		public boolean equals(Node n){
			if(n == null || this == null){
				return false;
			}
			if(n.word.equals(word))
				return true;
			else
				return false;
		}
		
		public String toString(){
			if(root == null){
				return "Null!";
			}
			String rb = "";
			String bal = "";
			if(red) rb = ", col: red";
			if(balance >= 100){
				bal = "";
			}else{
				bal = "bal: " + balance;
			}
			return word + "(" + bal + rb + ")" + ": " + defs;
		}
		
		
		
		/******************************
		 * Splay Methods
		 */
		public void splay(){
			if(this.up == null) {
				return;
			}
			Node parent = this.up;
			Node gp = this.up.up;
			
			while(this.up != null){
			
				parent = this.up;
				gp = this.up.up;
				//if(this.word.equals("jonquil")){ puts("\n" + this + "::" + parent + "}{" + this.up + "::" + gp + "}{" + this.up.up); tree(); puts("AKAKAKAKAKAK");}
				//if((gp != null && !parent.equals(gp.left) && !parent.equals(gp.right))){ puts("|||" + gp + ":::" + gp.left + ":::" + gp.right + ":::" + parent + "|||"); tree(); puts("____" + this + "____"); System.exit(0);}
				//if(gp != null && (parent.equals(gp.left) || parent.equals(gp.right))) puts("+++" + gp + "---" + gp.left + "---" + gp.right + "---" + parent + "+++");
				
				if(this.up.equals(root)){
					//zig
					
					//puts("uir" + "--------->" + blurf);
					if(parent.left != null && parent.left.equals(this)){
						//puts("zl");
						
						//left child
						this.rotRight();
					}else if(parent.right != null && parent.right.equals(this)){
						//puts("zr");
						//right child
						this.rotLeft();
					}else{
						// if it gets here something is wrong
						puts("GGGGGGGGRRRRRRRRRRRRRRG");
						
						puts("GGGGGGGGRRRRRRRRRRRRRRG");
						return;
					} 
				}else if(this.equals(parent.left) && parent.equals(gp.left)){
					//left zig-zig
					//puts("ziziL");
					parent.rotRight();
					this.rotRight();
				}else if(this.equals(parent.right) && parent.equals(gp.right)){
					//puts("ziziR");
					//right zig-zig
					parent.rotLeft();
					this.rotLeft();
				}else if(parent.equals(gp.left)){
					//puts("zizaLR");
					//left-right zig-zag
					this.rotLeft();
					this.rotRight();
				}else if(parent.equals(gp.right)){
					//puts("zizaRL");
					this.rotRight();
					this.rotLeft();
				}else{
					
					puts("AAAAAHHHHHHHHHHHHHHHHHHHHH!!!!!!!!!!!!!!!");
					list(gp.word,this.word);
					puts("AAAAAHHHHHHHHHHHHHHHHHHHHH!!!!!!!!!!!!!!!");
					return;
					//puts(parent.equals(gp.left) + "||||" + parent.equals(gp.right));
					//puts(blurf + "||||||||||");
					//root.left.rotRight();
					//tree();
					//return;
					//tree();
					//return;
					//something is wrong if it gets here
					//
				}
				//puts("j");
				//puts(this.toString() + "->" + this.up.toString());
			}
			root = this;
		}
		
		/******************************
		 * AVL Methods
		 */
		public void updateBalanceFactors(){
			Node n = this;
			while(n != null && n.balance < 100){
				if(n.left == null && n.right != null){
					n.balance = Math.abs(n.right.balance) + 1;
				}else if(n.right == null && n.left != null){
					n.balance = -1 - Math.abs(n.left.balance);
				}else if(n.left == null && n.right == null){
					n.balance = 0;
				}else{
					n.balance = n.right.balance + n.left.balance;
				}
				n = n.up;
			}
			
		}
		
		public void avlBalance(){
			Node n = this;
			while(n != null){
				if(n.balance > 1){
					
					//do a left rotation on right child (cause the child is what the rotation needs to take)
					if(n.right.right == null){
						//it must be a right left
						n.right.left.rotRight();
						n.right.rotLeft();
					}else{
						//it must be a right right
						n.right.rotLeft();
					}
				}else if(n.balance < -1){
					//do a right rotation on left child
					if(n.left.left == null){
						//then it's a left-right
						n.left.right.rotLeft();
						n.left.rotRight();
					}else{
						//it must be a left left
						n.left.rotRight();
					}
				}
				
				n = n.up;
			}
			
		}
		
		/*******************************
		 * Rotations
		 * (These methods should be used on the child, because the node that calls them will be raised a level)
		 */
		
		public void rotRight(){
			//'this' is the node that is raised a level by rotating
			if(this.up.equals(root)){
				Node parent = up;
				parent.left = this.right;
				this.right = parent;
				root = this;
				parent.up = this;
				this.up = null;
				this.right.updateBalanceFactors();
			}else{
				Node parent = up;
				Node gp = up.up;
				if(parent.equals(gp.left)){
					gp.left = this;
				}else{
					gp.right = this;
				}
				parent.up = this;
				this.up = gp;
				parent.left = this.right;
				this.right = parent;
				this.right.updateBalanceFactors();
			}
		}
		
		public void rotLeft(){
			if(this.up.equals(root)){
				Node parent = up;
				Node gp = up.up;
				parent.right = this.left;
				this.left = parent;
				root = this;
				parent.updateBalanceFactors();
				parent.up = this;
				this.up = null;
				this.left.updateBalanceFactors();
			}else{
				Node parent = up;
				Node gp = up.up;
				if(parent.equals(gp.left)){
					gp.left = this;
				}else{
					gp.right = this;
				}
				parent.up = this;
				this.up = gp;
				parent.right = this.left;
				this.left = parent;
				this.left.updateBalanceFactors();
			}
		}
		
	}
	
	public void puts(String s){
		System.out.println(s);
	}
	
	
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
