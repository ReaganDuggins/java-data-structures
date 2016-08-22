
public class Heap<E> implements PriQue<E> {
	private Node[] heapArray;
	private int maxSize;
	int currentSize;
	
	public Heap(int mx){
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}
	
	public boolean isEmpty(){
		return currentSize == 0;
	}
	
	public void insert(int pri, E data){
		// 0 indexed, so lchild == cur*2+1, rchild = cur*2+2
		////if current size + 1 is greater than .length, then increase array size
		heapArray[currentSize++] = new Node(pri, data);      //first put it at bottom of heap
		// then trickle up
		int i = currentSize-1;
		int parInd = (int)((i-1)/2.0);
		while(parInd >= 0 && heapArray[i].getKey() > heapArray[parInd].getKey()){
			
		}
		
	}
	
	public void trickleUp(int index){
		
	}
	
	public void trickleDown(int index){
		
	}
	
	public E remove(){
		return null;
	}
	
	
	
	public void displayHeap(){
		System.out.print("Heap Array: " );
		for(int m = 0; m < currentSize; m++)
			if(heapArray[m] != null)
				System.out.print(heapArray[m].getKey() + " ");
			else
				System.out.print("-- ");
		System.out.println();
		
		System.out.println("Heap: ");
		traverse(0,"");
		
	}
	
	public void traverse(int i, String dots){
		if(i > currentSize || i > heapArray.length || heapArray[i] == null)
			return;
		System.out.print(dots);
		System.out.println(heapArray[i].toString());
		if(i == 0){
			traverse(1, dots + "...");
			traverse(2, dots + "...");
		}else{
			traverse(i*2+1, dots + "...");
			traverse(i*2+2, dots + "...");
		}
	}
	
	public void pl(Object o){
		System.out.println(o);
	}
	public void p(Object o){
		System.out.print(o);
	}
	
	public static void main(String args[]){
		Heap h = new Heap(5);
		h.insert(1, "");
		h.insert(2, "");
		h.insert(3, "");
		h.displayHeap();
		System.out.println("_____________________________________________________________");
		
	}
	
}


