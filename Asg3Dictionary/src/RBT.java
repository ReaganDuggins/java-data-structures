import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class RBT {
	
	Node root;
	
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
					}catch(Exception e){
						System.out.println("Error for: \"" + line + "\"");
					}
				}else if(words[0].toLowerCase().equals("quit")){
					System.exit(0);
				}else if(words[0].toLowerCase().equals("")){
					
				}
			}
			scan.close();
		}catch(IOException e){
			System.out.println("Error reading file: " + fileName);
			e.printStackTrace();
		}
	}
	
	public void add(String word, String def){
		word = word.toLowerCase();//go to lowercase
		//first node
		if(root == null){
			root = new Node(word, def, false);
			return;
		}
		//other nodes
		Node n = root;
		while(true){
			//flip current node if it has two red children
			if(n.left != null && n.right != null){
				if(n.left.isRed() && n.right.isRed()){
					n.flip();
					root.setBlack();
				}
			}
			//if word already exists then just add any new definitions
			if(n.word.equals(word)){
				if(!n.hasDef(def)){
					//if the word already exists check if adding a new definition
					n.addDef(def);
					return;
				}else if(n.hasDef(def)){
					return;
				}
			}
			////word does not already exist;
			//check if need to go left or right
			if((n.word.compareTo(word)) > 0){
				//go or insert left
				if(n.left == null){
					n.left = new Node(word, def, true);
					n.left.up = n;
					root.setBlack();
					if(n.isRed()) redRedRules(n);  //because the new one will be red too
					return;
				}
				n = n.left;
			}else{
				//go or insert right
				if(n.right == null){
					n.right = new Node(word, def, true);
					n.right.up = n;
					root.setBlack();
					redRedRules(n);  //call red red on the parent of the new node
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
		System.out.println(word);
		while(n != null){
			if(n.word.equals(word)){
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
		specialInOrder(root, 0, startWord, endWord);
	}
	
	public void tree(){
		System.out.println();
		preOrder(root, 0, "root");
		System.out.println();
	}
	
	public void quit(){
		System.exit(0);
	}
	
	/////////Housekeeping//////////
	
	public void preOrder(Node n, int dots, String side){
		if(n == null)
			return;
		for(int i = 0; i < dots; i++){
			System.out.print("...");
		}
		System.out.print(side);
		System.out.println(n);
		preOrder(n.left, dots + 1, "L");
		preOrder(n.right, dots + 1, "R");
	}
	
	public void inOrder(){
		inOrderGo(root, 0);
	}
	
	public void inOrderGo(Node n, int dots){
		if(n == null)
			return;
		for(int i = 0; i < dots; i++){
			System.out.print("...");
		}
		inOrderGo(n.left, dots + 1);
		System.out.println(n);
		inOrderGo(n.right, dots + 1);
	}
	
	public void specialInOrder(Node n, int dots, String start, String stop){
		if(n == null)
			return;
		for(int i = 0; i < dots; i++){
			System.out.print("...");
		}
		specialInOrder(n.left, dots + 1, start, stop);
		if(n.word.compareTo(start) >= 0 && n.word.compareTo(stop) <= 0) System.out.println(n);
		specialInOrder(n.right, dots + 1, start, stop);
	}
	
	
	////////////////////////Node////////////////////////////
	class Node{
		//for alphabetical order, z is farthest to the right, a is farthest to the left
		String word;
		String defs;
		Node left;
		Node right;
		Node up;//refers to its parent
		boolean red;
		
		public Node(String word, String def, Node left, Node right, boolean red){
			this.word = word;
			this.left = left;
			this.right = right;
			this.red = red;
			this.defs = (def + "; ");
			//defs = new String[480];//in the Oxford dictionary some words have as many as 480 definitions
		}
		
		public Node(String word, String def, boolean red){
			defs = "";
			this.word = word;
			this.red = red;
		}
		
		public Node clone(Node n){
			return new Node(n.word,n.defs,n.left,n.right,n.isRed());
		}
		
		public boolean hasDef(String d){
			if(defs.split(";")[0] == null)	return false;
			for(int i = 0; i < defs.split(";").length; i++){
				if(defs.split(";")[i] == null) return false;
				if(defs.split(";")[i].equals(d)){
					return true;
				}
			}
			return false;
		}
		
		public void addDef(String d){
			for(int i = 0; i < defs.split(";").length; i++){
				if(defs.split(";")[i] == null){
					defs.split(";")[i] = d;
					return;
				}
			}
		}
		
		public String getDefs(){
			String s = "";
			for(int i = 0; i < defs.split(";").length; i++){
				if(defs.split(";")[i] != null) s += defs.split(";")[i] + "; ";
			}
			return s;
		}
		
		public boolean equals(Node n){
			if(n == null){
				return false;
			}
			if(n.word.equals(word))
				return true;
			else
				return false;
		}
		
		public void changeColor(){
			//change the color of this node
			if(isRed()) setBlack();
			else setRed();
		}
		
		public void flip(){
			//call this method on the parent of two red children, it will make this node red (assuming it is not root) and its two children black
			if(right == null || left == null){
				return;
			}
			setRed();
			right.setBlack();
			left.setBlack();
		}
		
		public String toString(){
			if(root == null){
				return "Null!";
			}
			String s = word.substring(0,1).toUpperCase() + word.substring(1);
			String r;
			if(red) r = "red";
			else r = "black";
			s += "(" + r + "):";
			for(int i = 0; i < defs.split(";").length; i++){
				if(defs.split(";")[i] == null){
					return s;
				}
				s += "   " + defs.split(";")[i];
			}
			return s;
		}
		
		public boolean isRed(){
			return red;
		}
		
		public void setRed(){
			red = true;
		}
		
		public void setBlack(){
			red = false;
		}
		
	}
	
	
	
	
	
	
	
	////////////Rotations////////////
	
	public Node rotRight(Node h){
		//rotate so that child goes up one level
		Node x = h.left;
        h.left = x.right;
        x.right = h;
        if(x.right != null){
        	x.red = x.right.red;
        	x.right.setRed();
        }
        x.defs = h.defs;
        x.word = h.word;
        return x;
		/*
		child.up.up.left = child;
		Node holder;
		if(child.right != null){
			holder = new Node(child.right.word,child.right.defs,child.right.left,child.right.right,child.right.isRed());
		}else{
			holder = null;
		}
		child.right = child.up;
		child.up.left = holder;*/
		
	}
	
	public void rotLeft(Node child){
		//reverse of rotRight, rotates left instead
		Node parent = child.up;
		Node grandparent = parent.up;
		Node holder;
		if(child.left != null){
			holder = new Node(child.left.word, child.left.defs, child.left.left, child.left.right, child.isRed());
		}else{
			holder = null;
		}
		if(grandparent != null && parent.equals(grandparent.left))
			grandparent.left = child;
		else if(grandparent != null)
			grandparent.right = child;
		child.up = grandparent;
		child.left = parent;
		parent.up = child;
		parent.right = holder;
	}

	public void redRedRules(Node parent){
		rotRight(parent);
	}
	
	
	
	public static void main(String args[]){
		RBT r = new RBT ();
		
		
		
		/*all basic tests worked
		//rout
		r.add("50", "");
		r.add("20", "");
		r.add("80", "");
		r.add("85", "");
		r.add("90", "");
		r.tree();
		r = new RBT();
		
		//rin
		
		r.add("50", "");
		r.add("60", "");
		r.add("20", "");
		r.add("70", "");
		r.add("65", "");
		r.tree();
		r = new RBT();
		//lout, parent needs to switch colors
		
		r.add("50", "");
		r.add("40", "");
		r.add("60", "");
		r.add("30", "");
		r.add("20", "");
		r.tree();
		r = new RBT();

		//lin
		
		r.add("50", "");
		r.add("40", "");
		r.add("60", "");
		r.add("30", "");
		r.add("35", "");
		r.tree();
		r = new RBT();*/
		
		r.add("50","");
		r.add("40","");
		r.add("30","");
		r.tree();
		
	}
}
