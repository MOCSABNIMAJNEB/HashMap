package hashmap;

/**
 * @author benjamin bascom
 * @date 2/10/2013
 * @assignment HashMaps, CIT594 Assignment 3
 */

import java.util.*;
import java.lang.Math;

@SuppressWarnings("rawtypes")
public class ListHashMap<K, V> implements Map<K, V> {
	private LinkedList _thisListOfLists[];
	private LinkedList<Pair<K,V>> _thisList;
	private int _size = 0, _len, _hashCode = 0;
	private ArrayList<K> keys = new ArrayList<K>();
	
	/**
	 * Constructor for List Hash Map Object
	 * @param size number of buckets in hash map
	 */
	ListHashMap(int size) {
		if (size < 1) {
			throw new IllegalArgumentException();
		}
		
		_len = size;
		_thisListOfLists = new LinkedList[size];
		
	}
	
	/**
	 * Inner class for storing key value pairs, Linked list parametereized to 
	 * hold these
	 * @param <K> key
	 * @param <V> value
	 */
	static class Pair<K, V> {
		K _key;
		V _value;
		
		Pair(K key, V value) {
			_key = key;
			_value = value;
		}
		
		public K getKey() {
			return _key;
		}
		
		public V getValue() {
			return _value;
		}
		
		public void setValue(V value) {
			_value = value;
		}
		
		// Pairs are considered 'equal' if their keys are equal ...
		@SuppressWarnings("unchecked")
		public boolean equals(Object obj) {
			if (!(obj instanceof Pair))
				return false;
			
			Pair<K, V> comp = (Pair<K, V>) obj;
			
			if (getKey().equals(comp.getKey())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	/**
	 * Clears this map by reinitializing the list of lists and resetting size
	 */
	@Override
	public void clear() {
		_size = 0;
		_thisListOfLists = new LinkedList[_len];
	}

	/**
	 * Checks to see if this key exists in our map
	 * @param Object key
	 */
	@Override
	public boolean containsKey(Object key) {
		makeKeys();
		return keys.contains(key);
	}
	
	/**
	 * Returns the number of items in our map
	 * @return size
	 */
	@Override
	public int size() {
		return _size;
	}

	/**
	 * Associates a value with a key in our map
	 * @param K key
	 * @param V value
	 * @return V old value for that key or null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		
		int bucket = Math.abs(key.hashCode() % _len);
		Pair<K, V> newPair = new Pair<K, V>(key, value);
		_thisList = _thisListOfLists[bucket];
		if (_thisList == null) {
			_size++;
			_hashCode += key.hashCode();
			_thisListOfLists[bucket] = new LinkedList<Pair<K,V>>();
			_thisListOfLists[bucket].add(new Pair<K,V>(key, value));
			return null;
		}
		
		Iterator<Pair<K,V>> it = _thisListOfLists[bucket].iterator();
		ArrayList<K> theseKeys = new ArrayList<K>();
		while (it.hasNext()) {
			K thisKey = it.next().getKey();
			theseKeys.add(thisKey);
		}
		
		if (!(theseKeys.contains(key))) {
			_hashCode += key.hashCode();
			_size++;
			_thisList.add(newPair);
			return null;
		} else {
			Pair <K,V> pair = (Pair<K,V>)_thisList.get(_thisList.indexOf(newPair));
			V oldValue = pair.getValue();
			pair.setValue(value);
			return oldValue;
		}
			
	}
	
	/**
	 * Gets the value for a key from our map
	 * returns null if the key is not in our map
	 * @param Object key
	 * @return V value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		int bucket = Math.abs(key.hashCode() % _len);
		_thisList = _thisListOfLists[bucket];
		if (_thisList == null) {
			return null;
		}
			
		Iterator<Pair<K,V>> items = _thisList.iterator();
		
		while (items.hasNext()) {
			Pair<K,V> item = items.next();
			if (item.getKey().equals(key)) {
				return item.getValue();
			}
		}

		return null;
	}

	/**
	 * Checks if this map is empty
	 */
	@Override
	public boolean isEmpty() {
		return (_size==0);
	}

	/**
	 * Returns a set of keys for our map
	 */
	@Override
	public Set<K> keySet() {
		return new KeySet<K>();
	}
	
	/**
	 * Gets all the keys in our map and puts them in an arrayList
	 * effectively taking a snapshot of the keys in our map when the
	 * method is called
	 */
	@SuppressWarnings("unchecked")
	private void makeKeys() {
		keys.clear();
		int i;
		for (i=0; i<_len; i++) {
			if (_thisListOfLists[i] != null) {
				Iterator<Pair<K,V>> items = _thisListOfLists[i].iterator();
				while (items.hasNext()) {
					keys.add(items.next().getKey());
				}
			}
		}
	}
	
	/**
	 * Tests if two maps are equal, we consider two maps equal
	 * if they have the same size and the same key/value pairs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		int i;
		K thisKey;
		V thisValue;
		if (!(obj instanceof Map))
			return false;
		
		Map comp = (Map) obj;
		
		if (!(comp.size() == size()))
			return false;
		
		for (i=0; i<_len; i++) {
			if (_thisListOfLists[i] != null) {
				Iterator<Pair<K,V>> it = _thisListOfLists[i].iterator();
				
				while (it.hasNext()) {
					Pair<K,V> thisPair = it.next();
					thisKey = thisPair.getKey();
					thisValue = thisPair.getValue();
					
					if (!(comp.containsKey(thisKey)) || !(comp.get(thisKey) == thisValue)) {
						return false;
					}
		
				}
			}
		}
		
		return true;
	}

	class KeySet<T> extends AbstractSet<T> {

		class keySetIterator implements Iterator<T>{
			Iterator<K> theseKeys = keys.iterator();
			
			@Override
			public boolean hasNext() {
				return theseKeys.hasNext();
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				return (T) theseKeys.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		}
		
		@Override
		public Iterator<T> iterator() {
			makeKeys();
			return new keySetIterator();
		}
		@Override
		public int size() {
			return ListHashMap.this.size();
		}
		
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean contains(Object o) {
			return ListHashMap.this.containsKey(o);
		}
		
		@Override 
		public void clear() {
			ListHashMap.this.clear();
		}
		
	}
	
	/**
	 * Hash code for this map
	 * @return sum of hashcodes of items in map
	 */
	@Override
	public int hashCode() {
		return _hashCode;
	}
	
	/* Unsupported Operations */
	@Override
	public boolean containsValue(Object arg0) {
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Set entrySet() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void putAll(Map arg0) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public V remove(Object arg0) {
		throw new UnsupportedOperationException();

	}


	@SuppressWarnings({ "unchecked" })
	@Override
	public Collection values() {
		throw new UnsupportedOperationException();

	}

	
}
