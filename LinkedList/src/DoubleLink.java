import java.util.InputMismatchException;
import java.util.Scanner;
public class DoubleLink {
	
	Node head;
	int length = 0;
	
	public void takeInput(){
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(!input.equals("done")){
			//reset input
			input = "";
			
			System.out.println("type \"done\" to exit, \"help\" for list of commands.");
			try{
				input = scan.nextLine().toLowerCase();
			}catch(Exception e){
				System.out.println("Error: Unknown command \"" + input + "\"");
				System.out.println(e.getStackTrace());
			}
			/********Print********/
			if(input.equals("print")){
				print();
			
			}else if(input.equals("print backwards")){
				printBackwards();
			/*********Add*********/
			}else if(input.equals("add")){
				
				System.out.println("Number to add:");
				try{
					long n = scan.nextLong();
					add(n);
					scan.nextLine();
				}
				catch(InputMismatchException e){
					scan.nextLine();/**for some reason, when value is initialized above,
										if the value throws this error then Scanner 
										saves the input for the next time scan.nextLine() 
										is called. So this scan.next is called so that 
										scan can "catch up" so to speak, preventing it 
										going through the loop twice before taking input
										*/
					System.out.println("Error: Not an integer!");
				}
			/**********Delete************/
			}else if(input.equals("delete")){
				System.out.println("Number to delete:");
				try{
					long n = scan.nextLong();
					delete(n);
					scan.nextLine();
				}catch(InputMismatchException e){
					scan.nextLine();
					System.out.println("Error: Not an integer!");
				}
			/*********Help*******/
			}else if(input.equals("help")){
				System.out.println("help\nadd\ndone\nprint\nprint backwards\nlength\nswap");
			}else if(input.equals("length")){
				System.out.println("Length: " + length);
			}
		}
		scan.close();
		System.out.println("Fin");
	}
	
	/*****
	 *Above here is the code for a simple interface if you want to use it.
	 **/
	
	public void add(long num){
		if(head == null){
			head = new Node(num);
			length++;
		}else if(num < head.n){
			//put before head
			Node nn = new Node(num, head, null);
			head = nn;
			length++;
			return;
		}else{
			//put after head
			if(head.next == null){
				//it becomes head.next
				head.next = new Node(num, null, head);
				length++;
			}else{
				Node fred = head;//fred likes to iterate through linked lists
				while(fred.next != null){
					if(num > fred.n && num < fred.next.n){
						//put new node between fred and fred.next
						Node nn = new Node(num, fred.next, fred);
						fred.next.prev = nn;
						fred.next = nn;
						length++;
						return;
					}
					fred = fred.next;
				}
				fred.next = new Node(num, null, fred);
				length++;
			}
		}
	}
	
	public boolean delete(long n){
		Node fred = head;
		while(fred != null){
			if(fred.n == n){
				if(fred.prev != null){
					if(fred.next != null){
						fred.prev.next = fred.next;
						fred.next.prev = fred.prev;
						return true;
					}else{
						fred.prev.next = null;
						return true;
					}
				}else{
					if(fred.next == null){
						head = null;
						return true;
					}else{
						head = fred.next;
						fred.next.prev = null;
						return true;
					}
				}
			}
			fred = fred.next;
		}
		return false;
	}
	
	public void swap(long num1, long num2){
		Node swap0 = head;
		Node swap1 = head;
		boolean s0 = false;
		boolean s1 = false;
		while(swap0 != null && swap1 != null){
			if(s0 && s1){
				//end loop if both are found
				break;
			}
			//assign if equal
		}
	}
	
	public void print(){
		if(head == null){
			System.out.println("Empty List");
			return;
		}
		for(Node fred = head; fred != null; fred = fred.next){
			System.out.println(fred);
		}
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

	
	/**********************Node*************************/
	
	private class Node {
		Node next;
		Node prev;
		long n;
		
		public Node(long n){
			this.n = n;
		}
		
		public Node(long n, Node nex, Node pre){
			next = nex;
			prev = pre;
			this.n = n;
		}
		
		public String toString(){
			return "Num: " + n;
		}
	}
	
	public static void main(String args[]){
		DoubleLink list = new DoubleLink();
		list.takeInput();
	}
	
}
