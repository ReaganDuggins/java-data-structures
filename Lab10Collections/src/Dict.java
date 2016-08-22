import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class Dict {
	
	TreeMap<String, String> t = new TreeMap<String, String>();
	
	public class QuitException extends Exception {}

	public void AddCmd(String word, String def) {
		if(word == null || def == null){
			System.out.println("Need a definition and a word!");
			return;
		}
		if(t.containsKey(word)){
			//if the definition is new then add it
			if(!t.get(word).contains(def)){
				//definition is new
				t.put(word, t.get(word) + ";" + def);
			}
		}else{
			t.put(word, def);
		}
		
	}

	public void FindCmd(String word) {
		System.out.println(word + " =>" + t.get(word));
	}

	public void ListCmd() {
		Set<Entry<String, String>> es = t.entrySet();
		for(Map.Entry<String,String> entry : es) {
			  String key = entry.getKey();
			  String value = entry.getValue();

			  System.out.println(key + " =>" + value);
			}
	}
	
	

	public void ListRangeCmd(String begin, String end) {
		Set<Entry<String, String>> es = t.entrySet();
		for(Map.Entry<String,String> entry : es) {
			  String key = entry.getKey();
			  String value = entry.getValue();

			  if(key.compareTo(begin) >= 0 && key.compareTo(end) <= 0) System.out.println(key + " =>" + value);
			}
	}

	public void LoadCmd(String filename) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(filename));
		try {
			String line = read.readLine();
			while (line != null) {
				parseCommand(line);
				line = read.readLine();
			}
		} catch (QuitException e) {

		} finally {
			read.close();
		}
	}

	public void parseCommand(String line) throws QuitException, IOException {
		int pos = line.indexOf(' ');
		String cmd = "";
		if(pos == -1)
			cmd = line;
		else
			cmd = line.substring(0, pos);
		
		if ("Add".equalsIgnoreCase(cmd)) {
            String remline = line.substring(pos).trim();
            int wpos = remline.indexOf(' ');
            String word = remline.substring(0,wpos);
            remline = remline.substring(wpos);
            AddCmd(word, remline);
		} else if ("Find".equalsIgnoreCase(cmd)) {
			String word = line.substring(pos).trim();
			FindCmd(word);
		} else if ("List".equalsIgnoreCase(cmd)) {
			
			if(pos == -1) {
				ListCmd();
			} else {
				String remline = line.substring(pos).trim();
				int wpos = remline.indexOf(' ');
	            String begin = remline.substring(0,wpos).trim();
	            String end = remline.substring(wpos).trim();
	            ListRangeCmd(begin,end);
			}
		} else if ("Load".equalsIgnoreCase(cmd)) {
			String filename = line.substring(pos).trim();
			LoadCmd(filename);
		} else if ("Quit".equalsIgnoreCase(cmd)) {
			throw new QuitException();
		} else {
                  if(cmd.trim().length() > 0) {
			    System.err.println("Invalid Command " + cmd);
                  }
		}
	}

	public void run() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.print("> ");
			String line = read.readLine();
			while (line != null) {
				parseCommand(line);
				System.out.print("> ");
				line = read.readLine();
			}
			
		} catch (QuitException e) {
            System.out.println("Bye");
		} finally {
			read.close();
		}
		
		read.close();
	}

	public Dict(String[] args) throws IOException {
		for (String fn : args) {
			LoadCmd(fn);
		}
	}

	public static void main(String[] args) throws IOException {
		Dict dict = new Dict(args);
		dict.run();
	}

}