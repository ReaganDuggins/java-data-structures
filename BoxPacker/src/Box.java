
public class Box {
	private String type;
	private int totWeight;// in ounces always
	private int cost;  // in pennies always
	private int currentWeight;
	private int unusedSpace;
	private LLStack<Book> books;
	
	public Box(String[] t){
		//t should be: {type, weight, cost}
		if(t.length != 3){
			System.out.println("Invalid Box constructor!");
			return;
		}
		setType(t[0]);
		setWeight(t[1]);
		setCost(t[2]);
		currentWeight = 0;
		setUnusedSpace(0);
		books = new LLStack<Book>();
	}
	
	public Box(Box b){
		//does not duplicate box, instead makes another box with the same type, totWeight, and cost as the input box
		type = b.type;
		totWeight = b.totWeight;
		cost = b.cost;
		currentWeight = 0;
		setUnusedSpace(0);
		books = new LLStack<Book>();
	}
	
	public boolean addBook(Book b){
		if((currentWeight + b.getWeight() > totWeight)){
			return false;
		}
		currentWeight += b.getWeight();
		books.push(b);
		return true;
	}
	
	public Book removeBook(){
		Book b = books.pop();
		if(b == null){
			return null;
		}
		currentWeight -= b.getWeight();
		return b;
	}
	
	public String toString(){
		LLStack<Book> holder = new LLStack<Book>();
		String s = "Box Type: " + type + ", Weight: " + totWeight + ", Cost: " + costString() + " Unused Space:" + getUnusedSpace() + "\n";
		Book b = removeBook();
		while(b != null){
			s += "  Weight: " + b.getWeight() + " Name: " + b.getTitle() + "\n";
			holder.push(b);
			b = removeBook();
		}
		while(!holder.isEmpty()){
			b = holder.pop();
			addBook(b);
		}
		return s + "\n--------------------------------------------------------------------------\n";
	}
	
	public boolean isEmpty(){
		return books.isEmpty();
	}
	
	public String costString(){
		String s = cost + "";
		s = "$" + s.substring(0, s.length()-2) + "." + s.substring(s.length()-2);
		return s;
	}
	
	//  Getters/Setters
	
	public int getCurWeight(){
		return currentWeight;
	}
	
	public void setBooks(LLStack<Book> sb){
		books = sb;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWeight() {
		return totWeight;
	}

	public void setWeight(String weight) {
		try{
			this.totWeight = Integer.parseInt(weight);
		}catch(Exception e){
			System.out.println("Invalid book weight!");
		}
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(String cost) {
		try{
			this.cost = Integer.parseInt(cost);
		}catch(Exception e){
			System.out.println("Invalid Book Cost");
		}
	}
	
	public int getUnusedSpace() {
		return totWeight - currentWeight;
	}

	public void setUnusedSpace(int unusedSpace) {
		this.unusedSpace = unusedSpace;
	}

}
