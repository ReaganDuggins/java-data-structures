public class DirectedGraph {

	boolean[][] adjMatrix;
	String[] locInd;
	
    public DirectedGraph() {
    // Add code
    	adjMatrix = new boolean[50][50]; 
    	locInd = new String[50];
    }

    void addRelation(String from, String to) {
    // Add code
    	//get indices of to and from, and if either is -1 then the matrix is full
    	int fromInd = getLocIndex(from);
    	int toInd = getLocIndex(to);
    	if(fromInd == -1 || toInd == -1){ 
    		System.out.println("Matrix Full");
    		return;
    	}
    	
    	//add the relation to the matrix
    	
    }

    void printAdjMatrix() {
    // Add code
    	
    }
    
    void calcWarshall() {
    // add code
    	
    }
    
    public int getLocIndex(String loc){
    	//returns the index corresponding to the inputted location
    	//if value is not in the array yet it puts it there
    	for(int i = 0; i < locInd.length; i++){
    		if(locInd[i] == null){
    			locInd[i] = loc;
    			return i;
    		}
    		if(locInd[i].equals(loc)){
    			return i;
    		}
    	}
    	return -1;
    }

    public static void main(String args[]) {
        DirectedGraph g = new DirectedGraph();
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
        g.addRelation("Knoxville", "Greenville");

        g.printAdjMatrix();
        g.calcWarshall();
        g.printAdjMatrix();
     }

}

     