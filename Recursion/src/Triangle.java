
public class Triangle {
	
	public static void tri(int m, int n){
		for(int i = 0; i < m; i++){
			System.out.print("*");
		}
		System.out.println();
		if(m < n)
			Triangle.tri((m+1), n);
		for(int i = 0; i < m; i++){
			System.out.print("*");
		}
		System.out.println();
		
		
	}
	
	
	public static void main(String args[]){
		int m = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		Triangle.tri(m, n);
	}
}
