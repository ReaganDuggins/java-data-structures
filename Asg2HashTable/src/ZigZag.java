import java.io.File;
import java.util.Scanner;


public class ZigZag {
	
	HashTable<String, String> table;
	
	public void makeTable(String path){
		File inF = new File(path);
		try{
			Scanner scan = new Scanner(inF);
			String line = scan.nextLine();
			
			while(scan.hasNextLine()){
				
				String k = "";
				String v = "";
				
				String[] words = line.split("[\\s\\-]+");
				
				for(int i = 0; i < words.length; i++){
					if(words[i].matches("[A-Za-z]+")){
						k += words[i];
					}else if(words[i].matches("[0-9]+")){
						v += words[i];
					}
				}
				
				table.put(k, v);
				
				
				line = scan.nextLine();
			}
			
		}catch(Exception e){
			System.out.println("File not found!");
		}
		table.dump();
		
	}
	
	public String find(String s){
		if(s.matches("\\d+")){
			System.out.println("Blurf");
			return table.getByValue(s);
		}else{
			return table.get(s);
		}
	}
	
	public ZigZag(){
		table = new HashTable<String, String>(1000);
	}
	
	public static void main(String args[]){
		ZigZag z = new ZigZag();
		z.makeTable(args[0]);
		System.out.println("\n________");
		System.out.println(z.find("SarahGillman"));
		System.out.println(z.find(z.find("SarahGillman")));
		
	}
}
