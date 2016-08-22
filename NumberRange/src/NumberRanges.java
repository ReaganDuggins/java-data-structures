import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberRanges {
	
	Node head;
	int length = 0;
	
	
	
	
	
	/***********Add Ranged Node*****************/
	public void add(long num){
		add(num, num);
	}
	public void add(long num0, long num1){
		// this adds new nodes sorted by n0. The clean method will merge mergable nodes.
		if(num0 > num1){
			//make sure num0 is the lower one
			long tmp = num1;
			num1 = num0;
			num0 = tmp;
		}
		if(head == null){
			head = new Node(num0,num1);
			return;
		}else if(num0 < head.n0){
			
			Node n = new Node(num0,num1,head,null);
			head = n;
			return;
		}else{
			Node cur = head;
			while(cur.next != null){
				if(cur.contains(num0,num1)){
					// new node is inside current node, don't add it
					return;
				}else{
					if(num0 >= cur.n0  && num0 < cur.next.n0){
						// insert new node as cur's next because it is between cur and cur.next
						Node n = new Node(num0,num1,cur.next,cur);
						cur.next.prev = n;
						cur.next = n;
						return;
					}
				}
				cur = cur.next;
			}
			//if you've gotten here then cur should be the last one on the list, cur.next should be null
			if(cur.contains(num0)){
				if(num1 > cur.n1){
					cur.n1 = num1;
					return;
				}
			}else{
				cur.next = new Node(num0,num1,null,cur);
			}
			
		}
	}
	
	public boolean delete(long n, long nn){
		Node cur = head;
		while(cur != null){
			if((cur.n0 == n) && (cur.n1 == nn)){
				if(cur.prev != null){
					if(cur.next != null){
						cur.prev.next = cur.next;
						cur.next.prev = cur.prev;
						return true;
					}else{
						cur.prev.next = null;
						return true;
					}
				}else{
					if(cur.next == null){
						head = null;
						return true;
					}else{
						head = cur.next;
						cur.next.prev = null;
						return true;
					}
				}
			}
			cur = cur.next;
		}
		return false;
	}
	
	/***Print Methods***/
	
	public void print(){
		if(head == null){
			System.out.println("Empty List");
			return;
		}
		for(Node cur = head; cur != null; cur = cur.next){
			System.out.print(cur + " ");
		}
		System.out.println();
	}
	
	public void printBackwards(){
		if(head == null){
			System.out.println("Empty List");
			return;
		}
		Node fred = head;
		while(fred.next != null){
			fred = fred.next;
		}
		for(Node i = fred; i != null; i = i.prev){
			System.out.println(i);
		}
	}

	public void mergeNodes(Node a, Node b){
		//assumes they can merge
		if(a.n1 <= b.n1){
			
			a.n1 = b.n1;
			if(b.next != null) b.next.prev = a;
			if(b != null) a.next = b.next;
		}else{
		}
	}
	
	public void clean(){
		Node n = head;
		if(head.next == null) return;
		while(n != null && n.next != null){
			while(n.next != null && n.canMerge(n.next)){
				mergeNodes(n, n.next);
			}
			n = n.next;
		}
	}
	
	public void getInput(Scanner scan){
		while(scan.hasNextLine()){
			String[] line = scan.nextLine().split("\\s+");
			if(line.length == 1){
				add(Long.parseLong(line[0]), Long.parseLong(line[0]));
			}else if(line.length == 2){
				add(Long.parseLong(line[0]), Long.parseLong(line[1]));
			}
		}
	}
	
	/**********************Node*************************/
	
	private class Node {
		Node next;
		Node prev;
		long n0;
		long n1;
		
		public Node(long n){
			this.n0 = n;
			this.n1 = n;
		}
		
		public Node(long n, long nn){
			if(n < nn){
				this.n0 = n;
				this.n1 = nn;
			}else{
				this.n0 = nn;
				this.n1 = n;
			}
		}
		
		public Node(long n, Node nex, Node pre){
			next = nex;
			prev = pre;
			this.n0 = n;
			this.n1 = n;
		}
		
		public Node(long n, long nn, Node nex, Node pre){
			next = nex;
			prev = pre;
			if(n < nn){
				this.n0 = n;
				this.n1 = nn;
			}else{
				this.n0 = nn;
				this.n1 = n;
			}
		}
		
		public boolean contains(long num){
			// returns true if num is in the node (inclusive)
			if(num >= n0 && num <= n1){
				return true;
			}
			return false;
		}
		public boolean contains(Node n){
			// if both nums of n are within this's range returns true
			if(this.contains(n.n0) && this.contains(n.n1)) return true;
			return false;
		}
		public boolean contains(long n0, long n1){
			// if both nums of n are within this's range returns true
			if(this.contains(n0) && this.contains(n1)) return true;
			return false;
		}
		
		public boolean canMerge(Node otherNode){
			// we know because of add() that otherNode's n0 is >= to this's n0
			// so it can merge if this.n1 is not less than otherNode.n0
			if(otherNode == null || n1 < (otherNode.n0-1)){
				return false;
			}
			return true;
		}
		public boolean canMerge(long n0, long n1){
			// we know because of add() that otherNode's n0 is >= to this's n0
			//also, this only checks if can merge to the right
			// so it can merge if this.n1 is not less than otherNode.n0
			if(n1 < (n0-1)){
				return false;
			}
			return true;
		}
		
		public String toString(){
			if(n0 == n1) return "(" + n0 + ")";
			return "(" + n0 + "<->" + n1 + ")";
		}
		
	}
	
	public static void main(String args[]){
			if(args.length != 1){
				System.out.println("Error: Please enter exactly one filename argument!");
				return;
			}
			NumberRanges nr = new NumberRanges();
			try{
				Scanner scan = new Scanner(new File(args[0]));
				while(scan.hasNextLine()){
					String line = scan.nextLine();
					line = line.trim();
					if(line.matches("(-\\d+||\\d+).*")){
						String[] nums = line.split("[\\s+]");
						try{
							//try to cast to longs
							
							if(nums.length == 2){
								long n0 = Long.parseLong(nums[0]);
								long n1 = Long.parseLong(nums[1]);
								nr.add(n0,n1);
							}else if(nums.length == 1){
								long n0 = Long.parseLong(nums[0]);
								nr.add(n0);
							}
							
						}catch(Exception e){
							
							continue;
						}
					}
					
				}
				nr.clean();
				nr.print();
				scan.close();
			}catch(Exception e){
				System.out.println("Error, can't read file!");
				e.printStackTrace();
			}
		
		
	}
	
}
