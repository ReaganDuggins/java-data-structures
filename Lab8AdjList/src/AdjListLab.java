public class AdjListLab {

	String[][] adj;
	
    public AdjListLab() {
    // Add code
    	adj = new String[50][50];
    }

    void addRelation(String from, String to) {
    // Add code
    	boolean foundFrom = false;
    	boolean foundTo = false;
    	int i = 0;
    	while(adj[i][0] != null){
    		if(adj[i][0].equals(from)){
    			addDest(i, to);
    			return;
    		}
    		if(adj[i][0].equals(to)){
    			addDest(i, from);
    			return;
    		}
    		i++;
    	}
    	//by this point we know that this relation is new
    	adj[i][0] = from;
    	addDest(i, to);
    }

    void printAdjList() {
    // Add code
    	int i = 0;
    	while(adj[i][0] != null){
    		System.out.print("\nRelations from " + adj[i][0] + "\n");
    		for(int j = 1; j < adj[i].length; j++){
    			if(adj[i][j] != null) System.out.println(adj[i][j]);
    		}
    		System.out.println();
    		i++;
    	}
    }
    
    public void addDest(int i, String dest){
    	//adds a destination to the next empty spot in a specific location's array
    	int j;
    	for(j = 1; adj[i][j] != null; j++){}
    	adj[i][j] = dest;
    }
   
    public static void main(String args[]) {
        AdjListLab g = new AdjListLab ();
        g.addRelation("Atlanta",  "Chattanooga");
        g.addRelation("Chattanooga",  "Nashville");
        g.addRelation("Chattanooga",  "Knoxville");
        g.addRelation("Atlanta",  "Birmingham");
        g.addRelation("Atlanta",  "Columbia");
        g.addRelation("Columbia", "Charleston");
        g.addRelation("Columbia",  "Greenville");
        g.addRelation("Greenville",  "Atlanta");
        g.addRelation("Charleston",  "Savanna");
        g.addRelation("Savanna",  "Atlanta");
        g.addRelation("Savanna", "Jacksonville");
        g.addRelation("Jacksonville", "Atlanta");
        g.addRelation("Greenville", "Knoxville");

        g.printAdjList();
     }

}

     