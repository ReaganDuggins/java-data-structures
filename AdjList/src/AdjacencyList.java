public class AdjacencyList {

	String[][] adjacencyList;
	
    public AdjacencyList() {
    	adjacencyList = new String[50][50];
    }

    public void addConnection(String start, String destination) {
		int cityIndex = 0;
    	while(getCity(0) != null){
    		if(getCity(cityIndex).equals(start)){
    			addDest(cityIndex, destination);
    			return;
    		}
    		if(getCity(cityIndex).equals(destination)){
    			addDest(cityIndex, start);
    			return;
    		}
    		cityIndex++;
    	}
		setCity(cityIndex, start);
    	addDest(cityIndex, destination);
    }

	void addDest(int cityIndex, String dest) {
		int connectionIndex;
		for (connectionIndex = 1; getConnection(cityIndex, connectionIndex) != null; connectionIndex++) {
		}
		setConnection(cityIndex, connectionIndex, dest);
	}

    String getCity(int cityIndex) {
    	if(cityExists(cityIndex)) return adjacencyList[cityIndex][0];
    	return null;
	}

	void setCity(int cityIndex, String cityName) {
		if(cityExists(cityIndex)) adjacencyList[cityIndex][0] = cityName;
	}

	String getConnection(int cityIndex, int connectionIndex) {
		if(cityExists(cityIndex)) return adjacencyList[cityIndex][connectionIndex];
		return null;
	}
	void setConnection(int cityIndex, int connectionIndex, String newName) {
		if(cityExists(cityIndex)) adjacencyList[cityIndex][connectionIndex] = newName;
	}

	String[] getConnections(int cityIndex) {
		if(!cityExists(cityIndex)) return null;
		return adjacencyList[cityIndex];
	}

	String[] getConnections(String city) {
    	int cityIndex = getCityIndex(city);
		if(!cityExists(cityIndex)) return null;
		return adjacencyList[cityIndex];
	}

	private int getCityIndex(String city) {
    	for(int cityIndex = 0; cityIndex < adjacencyList.length; cityIndex++) {
			String currentCity = adjacencyList[cityIndex][0];
			if(currentCity != null && currentCity.equals(city)) {
				return cityIndex;
			}
		}
		return -1;
	}

	private boolean cityExists(int cityIndex) {
    	return (adjacencyList[cityIndex][0] == null || !adjacencyList[cityIndex][0].equals(""));
	}

	void printAdjList() {
		int cityIndex = 0;
		while(getCity(cityIndex) != null){
			System.out.print("\nRelations from " + getCity(cityIndex) + "\n");
			for(int connectionIndex = 1; connectionIndex < getConnections(cityIndex).length; connectionIndex++){
				if(getConnection(cityIndex, connectionIndex) != null) System.out.println(getConnection(cityIndex, connectionIndex));
			}
			System.out.println();
			cityIndex++;
		}
	}

	public static void main(String args[]) {
		AdjacencyList adjacencyList = new AdjacencyList();
		adjacencyList.addConnection("Atlanta",  "Chattanooga");
		adjacencyList.addConnection("Chattanooga",  "Nashville");
		adjacencyList.addConnection("Chattanooga",  "Knoxville");
		adjacencyList.addConnection("Atlanta",  "Birmingham");
		adjacencyList.addConnection("Atlanta",  "Columbia");
		adjacencyList.addConnection("Columbia", "Charleston");
		adjacencyList.addConnection("Columbia",  "Greenville");
		adjacencyList.addConnection("Greenville",  "Atlanta");
		adjacencyList.addConnection("Charleston",  "Savanna");
		adjacencyList.addConnection("Savanna",  "Atlanta");
		adjacencyList.addConnection("Savanna", "Jacksonville");
		adjacencyList.addConnection("Jacksonville", "Atlanta");
		adjacencyList.addConnection("Greenville", "Knoxville");

		adjacencyList.printAdjList();
	}
}

     