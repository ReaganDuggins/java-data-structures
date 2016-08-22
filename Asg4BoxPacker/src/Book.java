
public class Book {
	private String isbn;
	private String title;
	private int weight;  // ounces always
	
	public Book(String[] t){
		if(t.length != 3){
			System.out.println("'" + t[0] + " | " + t[1] + " | " + t[2] + "' is not a valid book!");
			return;
		}
		setIsbn(t[0]);
		setTitle(t[1]);
		setWeight(t[2]);
	}
	
	public String toString(){
		return "Book Title: " + title + " | Weight: " + weight + " | ISBN: " + isbn + "";
	}
	
	
	// Getters/Setters

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		try{
			this.weight = Integer.parseInt(weight);
		}catch(Exception e){
			System.out.println("Invalid Book Weight!");
		}
	}
}
