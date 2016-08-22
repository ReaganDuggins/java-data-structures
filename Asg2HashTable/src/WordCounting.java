import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class WordCounting {
	
	HashTable<String, Integer> table = new HashTable<String, Integer>(10000);
	LinkedList badWords = new LinkedList();
	int totWords;
	
	public String wordify(String s){
		if(s.length() < 3) return s.toLowerCase();
		if(s.equals("'") || s.equals("~")) return "";
		if(s.charAt(s.length()-1) == s.charAt(s.length()-3) && s.charAt(s.length()-1) == '\''){
			s = s.substring(0, s.length()-1);
		}
		return s.toLowerCase();
				
	}
	
	public void useData(){
		Queue q = new Queue();
		for(int i = 0; i < table.hTable.length; i++){
			if(table.hTable[i] != null){
				PairClass[] pairs = table.hTable[i].returnAllPairs();
				for(int j = 0; j < pairs.length; j++){
					q.add(wordify(pairs[j].f.toString()));
				}
			}
		}
		q = new Radix(q).sort();
		
		while(!q.isEmpty()){
			String s = q.remove();
			if(s != null && s.length() > 0){
				try{
					int n = table.get(s);
					System.out.println(s + " happens " + table.get(s) + " times");
				}catch(Exception e){
					
				}
				
			}
		}
		
		System.out.println("Total Words: " + totWords);
		System.out.println("");
	}
	
	public int countWords(){
		int cnt = 0;
		for(int i = 0; i < table.hTable.length; i++){
			if(table.hTable[i] != null)
				cnt += table.hTable[i].numberContained();
		}
		return cnt;
	}
	
	public void readFile(String filePath){
		try {
			Scanner scan = new Scanner(new File(filePath)).useDelimiter("[^a-zA-Z]+");
			String line;
			while(scan.hasNext()){
				String word = scan.next().toLowerCase();
				int x = 0;
				if(table.get(word) != null){
					x = table.get(word);
				}
				table.putQuad(word.toLowerCase(), x+1);
				totWords += 1;
			}
			
			scan.close();
			//useData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void sort(){
		
	}
	public WordCounting(){
		totWords = 0;
	}
	
	
	public static void main(String args[]){
		WordCounting w = new WordCounting();
		w.readFile(args[0]);
		w.useData();
		
	}
}
