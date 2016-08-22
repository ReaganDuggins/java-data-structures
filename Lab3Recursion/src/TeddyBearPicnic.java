import java.util.Scanner;


public class TeddyBearPicnic {
	
	
	public boolean picnic(int bears, boolean flag){
		boolean b = false;
		if(bears == 42) return true;
		if(bears < 42) return false;
		if(bears % 2 == 0) b = picnic(bears/2,false);
		if((bears % 3) == 0 || (bears % 4) == 0){
			if(bears % 10 != 0 && (bears % 100)/10 != 0){
				b = picnic(bears - ((bears % 10)*((bears % 100)/10)),false);
			}
		}
		if(bears % 5 == 0){
			b = picnic(bears - 42,false);
		}
		return b;
	}
	
	public void teddy(){
		Scanner scan = new Scanner(System.in);
		System.out.println("How many teddybears do you give me?");
		int bears = scan.nextInt();
		
		boolean fin = picnic(bears,false);
		
		if(fin){
			System.out.println("Victory! You now have 42 bears!");
		}else{
			System.out.println("Defeat...");
		}
		
		
		scan.close();
	}
	
	
	
	public static void main(String args[]){
		TeddyBearPicnic t = new TeddyBearPicnic();
		t.teddy();
		
	}
	
}
