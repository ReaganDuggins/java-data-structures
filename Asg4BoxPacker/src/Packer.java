import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Packer {
	
	//The heap will grow by 5 each time you add an element that would be over the maxSize
	
	Scanner getUI = new Scanner(System.in);
	Box[] boxTypes;
	
	public void printBoxes(LLStack<Box> fullBoxes){
		int totWeight = 0;
		int totUnusedWeight = 0;
		int totCost = 0;
		int smalls = 0;
		int meds = 0;
		int larges = 0;
		int giants = 0;
		int totBoxes = 0;
		LLStack<Box> printedBoxes = new LLStack<Box>();
		while(!fullBoxes.isEmpty()){
			Box box = fullBoxes.pop();
			System.out.println(box);
			totWeight += box.getCurWeight();
			totUnusedWeight += box.getWeight() - box.getCurWeight();
			totCost += box.getCost();
			if(box.getType().toLowerCase().equals("small")){
				smalls++;
			}else if(box.getType().toLowerCase().equals("medium")){
				meds++;
			}else if(box.getType().toLowerCase().equals("large")){
				larges++;
			}else if(box.getType().toLowerCase().equals("giant")){
				giants++;
			}
			totBoxes++;
			printedBoxes.push(box);
		}
		String s = totCost + "";

		try{
			s = "$" + s.substring(0, s.length()-2) + "." + s.substring(s.length()-2);
		}catch(java.lang.StringIndexOutOfBoundsException e){
			s = "$0.00";
		}
		System.out.println("Total weight: " + totWeight + "\nTotal unused: " + totUnusedWeight + "\nTotal cost: " + s + "\n\nSmall boxes: " + smalls + "\nMedium boxes: " + meds + "\nLarge boxes: " + larges + "\nGiant boxes: " + giants + "\n--------------------------\nTotal Boxes: " + totBoxes);
		
		//put the printed boxes back into fullBoxes
		while(!printedBoxes.isEmpty()){
			fullBoxes.push(printedBoxes.pop());
		}
	}
	
	public void consolidateBoxes(LLStack<Box> fullBoxes){
		//this method takes stack of full boxes and if any box can be replaced by a smaller one then that is done
		pl(fullBoxes.isEmpty());
		LLStack<Box> holder = new LLStack<Box>();
		while(!fullBoxes.isEmpty()){
			//loop through all the boxes
			//get the box
			Box b = fullBoxes.pop();
			int i = 0;
			//check the appropriate size of the box
			
			for(int x = boxTypes.length-1; x >= 0; x--){
				if(boxTypes[x].getWeight() > b.getCurWeight()){
					i = x;
				}
			}
			
			//if it is not the largest size then replace it, else put it into holder and continue
			if(i < boxTypes.length-1){
				Box bx = new Box(boxTypes[i]);
				b.setCost(bx.getCost() + "");
				b.setType(bx.getType());
				b.setWeight(bx.getWeight() + "");
				b.setUnusedSpace(bx.getWeight() - b.getCurWeight());
				holder.push(b);
			}else{
				holder.push(b);
			}
			
		}
		printBoxes(holder);
	}
	
	public void packBoxes(Book[] books){
		PriQueue<Book> h = new PriQueue<Book>();
		LLStack<Box> fullBoxes = new LLStack<Box>();
		//add the books to the heap
		// it works here too
		for(int i = 0; i < books.length; i++){
			h.insert(books[i].getWeight(), books[i]);
		}
		// Still working
		//books are in heap, now pull them off and put them in boxes
		books = new Book[books.length];
		
		Box bx = new Box(boxTypes[boxTypes.length-1]);
		while(!h.isEmpty()){
			Book b = h.remove();
			
			if(!bx.addBook(b)){
				fullBoxes.push(bx);
				bx = new Box(boxTypes[boxTypes.length-1]);
				if(!bx.addBook(b))
					System.out.println("A book over 160 weight :O !!!");
			}
			
		}
		fullBoxes.push(bx);
		
		
		//now there are no more books
		//move to smaller box if necessary
		consolidateBoxes(fullBoxes);
		
	}
	
	public void readBoxes(String boxfilename,String bookfilename){
		
		Scanner boxRead;
		Scanner bookRead;
		
		//initialize scanners
		try{
			boxRead = new Scanner(new File(boxfilename));
		}catch(IOException e){
			System.out.println("Error Reading Box File!");
			return;
		}
		try{
			bookRead = new Scanner(new File(bookfilename));
		}catch(IOException e){
			System.out.println("Error Reading Book File");
			boxRead.close();
			return;
		}
		
		
		
		//save list of boxes
		String boxList = "";
		while(boxRead.hasNextLine()){
			boxList = boxList + boxRead.nextLine() + ";";
		}
		String[] boxString = boxList.split(";");
		boxTypes = new Box[boxString.length];
		for(int i = 0; i < boxString.length; i++){
			boxTypes[i] = new Box(boxString[i].split("/"));
		}
		
		//save list of books
		String bookList = "";
		while(bookRead.hasNextLine()){
			bookList = bookList + bookRead.nextLine() + ";";
		}
		//so far it works System.out.println("|||||||||||||||||||||||||||||||||||||||" + String.join("\n", bookList.split(";")) + "|||||||||||||||||||||||||||||||||||||||");
		String[] bookString = bookList.split(";");
		Book[] books = new Book[bookString.length];
		for(int i = 0; i < bookString.length; i++){
			books[i] = new Book(bookString[i].split("/"));
			
		}
		
		
		//It's correct here too...
			
		
		
		//Start packing
		packBoxes(books);
		
		//close scanners
		boxRead.close();
		bookRead.close();
	}
	
	public void pl(Object o){
		System.out.println(o);
	}
	public void p(Object o){
		System.out.print(o);
	}
	
	public static void main(String args[]){
		Packer bp = new Packer();
		if(args.length < 1){
			System.out.println("No args, assuming 'shipOptions1.txt' and 'book1.txt'");
			bp.readBoxes("shipOptions1.txt", "book1.txt");
		}else{
			if(args.length > 1){
				bp.readBoxes(args[0],args[1]);
			}else{
				System.out.println("Only one arg, assuming 'book1.txt' as book file");
				bp.readBoxes(args[0], "book1.txt");
			}
		}
	}
}
