/**
* 
The shuttle sort is O(n^2), but less comparisons than bubble sort.

The average performance of this sort is so dependant on how sorted the data was beforehand that an exact equation for efficiency is difficult to find
	
This has less comparisons than bubble sort, about the same as an insertion sort, and
	somewhat slower than a selection sort.
* 
**/
import java.util.Arrays;
import java.util.Random;


public class ShuttleSort {
	
	/**
	 * Sort Methods
	 */
	private int comparisons;
	private int swaps;
	
	public ShuttleSort(){
		comparisons = swaps = 0;
	}
	
	public void sort(int[] a){
		System.out.print("Unsorted: ");
		printa(a);
		
		for(int i = 1; i < a.length; i++){
			for(int j = i; j > 0; j--){
				if(a[j] < a[j-1]){
					swaps++;
					comparisons++;
					int tmp = a[j];
					a[j] = a[j-1];
					a[j-1] = tmp;
				}else{
					comparisons++;
					break;
				}
			}
		}
			
		System.out.print("Sorted: ");
		printa(a);
		System.out.println("Comparisons: " + comparisons + "\nSwaps: " + swaps);
	}
	
	public boolean shuttle(int[] a, int n){
		int j = 0;
		for(; (n < a.length); n++, j++){
			if(a[n-1] > a[n]){
				//swap
				int temp = a[n-1];
				a[n-1] = a[n];
				a[n] = temp;
			}else{
				break;
			}
		}
		if(j == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Utility Methods
	 * int tmp = a[j+1];
					a[j+1] = a[j];
					a[j] = tmp;
					System.out.println("j: " + j + " - j+1: " + (j+1));
					printa(a);
	 */
	
	public int[] generateRandomArray(int length){
		int[] list = new int[length];
		Random r = new Random();
		for(int i = 0; i < length; i++){
			int n = r.nextInt(899)+100;
			list[i] = n;
		}
		return list;
	}
	
	public static void printa(int[] a){
		for(int i = 0; i < a.length; i++){
			if(i < a.length - 1)
				System.out.print(a[i] + "-");
			else
				System.out.print(a[i]);
		}
		System.out.println();
	}
	
	/**
	 * Main
	 */
	
	
	public static void main(String args[]){
		ShuttleSort ss = new ShuttleSort();
		int[] l = ss.generateRandomArray(5);
		ss.sort(l);
	}
}
