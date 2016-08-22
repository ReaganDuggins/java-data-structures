
public class BSTArray {
	
	int[] bst;
	
	public BSTArray(){
		bst = new int[100];
	}
	
	public int getLeftIndex(int i){
		return ((2*i) + 1);
	}
	
	public int getRightIndex(int i){
		return ((2*i) + 2);
	}
	
	public void insert(int key){
		int i = 0;
		while(true){
			if(bst[i] == 0){
				bst[i] = key;
				return;
			}
			if(key < bst[i]){
				i = getLeftIndex(i);
			}else if(key > bst[i]){
				i = getRightIndex(i);
			}
		}
	}
	
	public void preOrder(int i, int dots){
		if(i >= bst.length || bst[i] == 0)
			return;
		for(int j = 0; j < dots; j++){
			System.out.print("...");
		}
		System.out.println(bst[i]);
		preOrder(getLeftIndex(i), dots + 1);
		preOrder(getRightIndex(i), dots + 1);
	}
	
	public static void main(String[] args){
		BSTArray bst = new BSTArray();
		bst.insert(50);
		bst.insert(25);
		bst.insert(75);
		bst.insert(10);
		bst.insert(15);
		bst.insert(5);
		bst.insert(53);
		bst.insert(29);
		bst.insert(79);
		bst.insert(78);
		bst.insert(111);
		bst.insert(33);
		bst.preOrder(0, 0);
	}
	
}
