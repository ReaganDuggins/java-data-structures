public interface Table<K, V> {
	// add the value to the table at key
	void put(K key, V value);
	// retrieve the value associated with the key
	// if the key is not found return a null
	V get(K key);
	// get entries <K,V> one after the other
	// note that since this is a HASH table the entries will not be returned 
	// in any particular order
	Pair<K,V> getFirstEntry();
	Pair<K,V> getNextEntry();
	// show contents of table
	void dump();
}