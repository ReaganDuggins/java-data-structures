public class DirectedGraph {

	boolean[][] matrix;
	String[] locations;
	
    public DirectedGraph() {
    // Add code
    	matrix = new boolean[50][50];
    	locations = new String[50];
    	for(int i = 0; i < locations.length; i++){
    		locations[i] = "";
    	}
    }

    void addRelation(String from, String to) {
    // Add code
    	int f = locInd(from);
    	int t = locInd(to);
    	matrix[f][t] = true;
    	//matrix[t][f] = true;//comment this if it is not supposed to be bidirectional
    }

    void printAdjMatrix() {
    // Add code
    	if(locations[0] == null){
    		System.out.println("Empty Matrix");
    		return;
    	}
    	for(int i = 0; i < 50; i++){
    		if(!locations[i].equals(""))
    			System.out.println("\nRelations from " + locations[i]);
    		for(int j = 0; j < 50; j++){
    			if(matrix[i][j]){
    				System.out.println("   " + locations[j]);
    			}
    		}
    	
    	}
    }
    
    void calcWarshall() {
    // add code
    	for(int k = 0; k < 50; k++){
    		for(int i = 0; i < 50; i++){
    			for(int j = 0; j < 50; j++){
    	    		if(matrix[i][k] && matrix[k][j]){
    	    			matrix[i][j] = true;
    	    		}
    	    	}
        	}
    	}
    }
    
    int locInd(String loc){
    	//returns the index of the location, or -1 if there is no more room
    	int i;
    	for(i = 0; i < 50 && !locations[i].equals(""); i++){
    		if(loc.equals(locations[i]))
    			return i;
    	}
    	if(i < 50){
    		locations[i] = loc;
    		return i;
    	}else
    		return -1;
    }

    public static void main(String args[]) {
        DirectedGraph g = new DirectedGraph();
        g.addRelation("Atlanta",      "Chattanooga");
        g.addRelation("Chattanooga",  "Nashville");
        g.addRelation("Chattanooga",  "Knoxville");
        g.addRelation("Atlanta",      "Birmingham");
        g.addRelation("Atlanta",      "Columbia");
        g.addRelation("Columbia",     "Charleston");
        g.addRelation("Columbia",     "Greenville");
        g.addRelation("Greenville",   "Atlanta");
        g.addRelation("Charleston",   "Savanna");
        g.addRelation("Savanna",      "Atlanta");
        g.addRelation("Savanna",      "Jacksonville");
        g.addRelation("Jacksonville", "Atlanta");
        g.addRelation("Knoxville",    "Greenville");
        g.printAdjMatrix();
        System.out.println("---------------------------------------------");
        g.calcWarshall();
        g.printAdjMatrix();
     }

}

     