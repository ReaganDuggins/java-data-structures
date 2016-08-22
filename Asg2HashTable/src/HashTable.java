

public class HashTable<K,V> implements Table<K,V>{

	LinkedList[] hTable;
	
	public HashTable(int size){
			hTable = new LinkedList[size];
		
	}
	
	public void put(Object key, Object value) {
		int k = Math.abs(key.hashCode() % hTable.length);
		//if(k < 0){
		//	k += hTable.length;
		//}
		if(hTable[k] == null){
			hTable[k] = new LinkedList();
			hTable[k].add(new PairClass(key,value));
		}else{
			if(!hTable[k].hasKey(key)){
				hTable[k].add(new PairClass(key,value));
			}else{
				hTable[k].setValue(key, value);
			}
		}
	}
	
	public void putQuad(Object key, Object value) {
		int k = Math.abs(key.hashCode() % hTable.length);
		//if(k < 0){
		//	k += hTable.length;
		//}
		if(hTable[k] == null){
			hTable[k] = new LinkedList();
			hTable[k].add(new PairClass(key,value));
		}else{
			if(!hTable[k].hasKey(key)){
				if(hTable[k] == null){
					hTable[k].add(new PairClass(key,value));
				}else{
					
					for(int i = 0; hTable[k] != null; i++){
						k = (k + i) % hTable.length;
					}
					
				}
			}else{
				hTable[k].setValue(key, value);
			}
		}
	}
	
	public void put(PairClass p){
		//Open Chain
		int k = p.f.hashCode() % hTable.length;
		if(k < 0){
			k += hTable.length;
		}
		if(hTable[k] == null)
			hTable[k] = new LinkedList();
		if(hTable[k].hasKey(p.f)){
			System.out.println("Blurf");
			hTable[k].setValue(p.f, p.s);
		}else{
			hTable[k].add(p);
		}
	}
	
	public void setValue(K key, V value){
		//set an existing value in the hash table
		int k = key.hashCode() % hTable.length;
		if(k < 0){
			k += hTable.length;
		}
		hTable[k].setValue(key, value);
	}

	public V get(K key) {
		try{
			int k = key.hashCode() % hTable.length;
			if(k < 0){
				k += hTable.length;
			}
			LinkedList l = hTable[k];
			LinkedList.Node n = l.head;
			while(!n.p.f.equals(key) && n != null){
				n = n.next;
			}
			return (V) n.p.s;
		}
		catch(NullPointerException e){
			//System.out.println("Error: Key not found!");
			return null;
		}
	}
	
	public K getByValue(V value) {
		for(int i = 0; i < hTable.length; i++){
			if(hTable[i] != null){
				if(hTable[i].getByValue(value) != null)
					return (K) hTable[i].getByValue(value).f;
			}
		}
		return null;
	}

	
	public PairClass getFirstEntry() {
		for(int i = 0; i < hTable.length; i++){
			if(hTable[i] != null)
				return new PairClass(i, hTable[i]);
		}
		return null;
	}

	
	public Pair getNextEntry() {
		//???//
		return null;
	}

	
	public void dump() {
		for(int i = 0; i < hTable.length; i++){
			if(hTable[i] != null){
				System.out.println("  ");
				System.out.print(i + " ");
				hTable[i].print();
			}
		}
	}
	
	public Queue toQ(){
		Queue q = new Queue();
		for(int i = 0; i < hTable.length; i++){
			if(hTable[i] != null){
				q.add(hTable[i].head.p.f.toString().toLowerCase());
			}
		}
		return q;
	}
	
	public void dumpSort(){
		//radix sorts the entries based on key
		Queue inq = toQ();
		Queue[] queues = new Queue[28];//0 is the word is already ended, 1 is 'a', 28 is "'"
		for(int i = 25; i < 0 || inq.isEmpty(); i--){//25 is likely to be large enough for any common word
			while(!inq.isEmpty()){
				String word = inq.remove();
				if(word.length() > i){
					int n = (int) word.charAt(i)-96;
					queues[n].add(word);
				}else{
					queues[0].add(word);
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		//test the hashtable
		HashTable ht = new HashTable(17);
		PairClass p = new PairClass("d", 1);
		PairClass p00 = new PairClass("c", 3);
		PairClass p0 = new PairClass("z", 1);
		PairClass p1 = new PairClass("b", 1);
		ht.put(p);
		ht.put(p0);
		ht.put(p1);
		ht.put(p00);
		
	}
	
	
	
}
