/**
 * 
 * @author Harakirinoninja
 * Shortest Path for 10 Cities: AGJFCEBIHDA
 * Length of Above Path: 390
 * Largest Number of Cities Handled: ~20 (currently calculating for 20, and is currently at approximately 320000 permutations found
 * if it breaks or succeeds I will let you know in a comment, because this probably will not be done calculating in time...)
 * Why can't I run more cities? The calculations to see all the permutations take a REALLY long time.
 */

public class TravelingStarter {
	
	int numCities;
	int map[][]={
			{0, 6, 0, 0, 0, 0, 99, 0, 20, 0, 0, 0, 0, 58, 0, 0, 0, 0, 92, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 82, 0, 0, 0, 0, 74, 0, 93, 16, 54, 39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 15, 77, 0, 0, 0, 0, 99, 0, 75, 0, 0, 0, 0, 0, 0, 0, 92, 0, 0, 20, 52, 0},
			{1, 0, 69, 0, 0, 0, 0, 79, 0, 0, 0, 0, 0, 0, 89, 0, 0, 0, 97, 14, 0, 0, 0, 0, 0, 0},
			{45, 13, 0, 0, 0, 0, 87, 0, 77, 31, 0, 0, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{67, 0, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 86, 0, 0, 0, 26, 0, 0, 17, 0, 0, 96, 0, 0},
			{1, 0, 0, 0, 0, 66, 0, 0, 0, 39, 0, 0, 0, 0, 46, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 80, 21, 0, 92, 0, 0, 0, 0, 0, 0, 7, 92, 0, 0, 27, 0, 0, 0, 31, 0, 0, 0, 0, 0},
			{13, 0, 88, 0, 0, 0, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 37},
			{1, 0, 0, 0, 74, 50, 0, 0, 0, 0, 77, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 65, 0, 0},
			{1, 95, 49, 0, 0, 0, 52, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 85, 0, 0, 0, 0, 0, 0},
			{1, 0, 37, 37, 0, 0, 0, 0, 98, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62, 0, 0, 46, 0, 80, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 22, 0, 0, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0},
			{1, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 44, 43, 0},
			{1, 0, 0, 8, 0, 0, 0, 0, 0, 68, 0, 0, 0, 21, 0, 0, 0, 0, 0, 0, 72, 0, 0, 0, 76, 0},
			{1, 20, 0, 0, 0, 0, 52, 36, 0, 0, 21, 0, 0, 17, 0, 0, 0, 71, 0, 0, 0, 0, 71, 6, 0, 0},
			{1, 0, 0, 0, 92, 0, 96, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0},
			{1, 0, 0, 81, 0, 0, 6, 0, 82, 0, 58, 0, 0, 0, 0, 0, 94, 0, 47, 0, 0, 0, 81, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 60, 59, 87, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 52, 69, 0, 50, 0, 0, 64, 0, 0, 43, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 57, 0, 0, 0},
			{1, 0, 24, 0, 0, 0, 0, 68, 0, 0, 0, 0, 52, 98, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 3, 51, 0, 26, 0, 0, 0, 0, 0, 46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95},
			{1, 0, 0, 0, 0, 99, 0, 0, 0, 0, 68, 90, 82, 0, 0, 85, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 72, 24, 66, 0, 28, 0, 0, 0, 74, 0, 0, 0, 38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 98, 0, 0, 0, 0, 0, 69, 16, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 57, 0, 0, 6, 40, 0, 23, 0, 0, 0, 0, 0, 88, 34, 0, 0, 28, 0, 38, 0},
		};
	
	
	public void calculate(int cities){
		
		brutePath(cities);
		
	}
	
	public int calcPath(String path){
		path = path.toUpperCase();
		int totalLength = 0;
		for(int i = 0; i < path.length()-1; i++){
			int loc0 = path.charAt(i)-65;
			int loc1 = path.charAt(i+1)-65;
			//System.out.println("::" + loc0 + "::" + loc1 + "::" + map[loc0][loc1]);
			if(map[loc0][loc1] == 0) return 999999;
			totalLength += map[loc0][loc1];
			
		}
		return totalLength;
		
	}
	
	public String[] permString(String path){
		Permutate.permuteString("", path);
		String s = Permutate.perms;
		return s.split(";");
	}
	
	public void brutePath(int cities){
		//
		String path = "";
		for(int i = 1; i < cities; i++){
			path += (char)(65 + i);
		}
		System.out.println("Calculating each permutation...");
		String[] perms = permString(path);
		System.out.println(perms.length + " permutations found.");
		String shortestPath = "";
		int shortestPathLength = 999999;
		for(int i = 0; i < perms.length; i++){
			//for each permutation calculate the path, and if this path is shorter than current shortest, then replace it
			if(perms[i].substring(0,4).equals("null")){
				perms[i] = perms[i].substring(4);
			}
			System.out.print(perms[i] + " is ");
			int curPath = calcPath(perms[i]);
			System.out.print(curPath + " long.\n");
			if(curPath < shortestPathLength){
				shortestPathLength = curPath;
				shortestPath = perms[i];
			}
		}
		System.out.println( "Shortest Path: " + shortestPath.toString() + "\nShortest Path Length: " + shortestPathLength);
	}
	
	
	
	/******************/

	TravelingStarter(int numCities){
		this.numCities = numCities;
	}
	
	public void printMap(){
		for(int i=0; i < numCities; i++){
			for(int j= 0; j < numCities; j++){
				System.out.printf(" %02d", map[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		TravelingStarter ts = new TravelingStarter(10);
		ts.printMap();
		//check args
		if(args.length > 0){
			try{
				ts.calculate(Integer.parseInt(args[0]));
			}catch(Exception e){
				System.out.println("Invalid args, defaulting to 10 cities.");
				ts.calculate(10);
			}
		}else{
			System.out.println("\nNo args, defaulting to 10.\n");
			ts.calculate(10);
		}
	}
}