package hashmap;
/**
 * @author benjamin bascom
 * @date 2/10/2013
 * @assignment HashMaps, CIT594 Assignment 3
 */

import java.util.*;

@SuppressWarnings("rawtypes")
public class TreeHashMap<K extends Comparable<K>,V> implements Map<K, V> {
	private SortedBinaryTree<K,V> _thisListOfLists[];
	private SortedBinaryTree<K,V> _thisList;
	private int _size = 0, _len, _hashCode = 0;
	private ArrayList<K> keys = new ArrayList<K>();
	
	@SuppressWarnings("unchecked")
	TreeHashMap(int size) {
		if (size < 1) {
			throw new IllegalArgumentException();
		}
		
		_len = size;
		_thisListOfLists = new SortedBinaryTree[size];
	}
	
	/**
	 * Class for storing key value pairs in a binary tree
	 * @param <K> key 
	 * @param <V> value
	 */
	static class SortedBinaryTree<K extends Comparable<K>, V> {
		K _key;
		V _value;
		SortedBinaryTree<K,V> _left, _right;
		
		SortedBinaryTree(K key, V value) {
			_key = key;
			_value = value;
		}
		
		/**
		 * Insert a key value pair into this tree
		 * @param tree containing the key/value pair we're interested in
		 * @return Value that was previously associated with this key in the map or null if this key was not previously in the map
		 */
		public V insert(SortedBinaryTree<K,V> tree) {
			 if (tree.getKey().compareTo(getKey()) == 0 ) {
				V oldValue = getValue();
				_value = tree.getValue();
				return oldValue;
				
			} else if (tree.getKey().compareTo(getKey()) > 0) {
				if (getRight()==null) {
					_right = tree;
				}
				else {
					_right.insert(tree);
				}
			} else if (tree.getKey().compareTo(getKey()) < 0) {

				if (getLeft()==null) {
					_left = tree;
				}
				else {
					_left.insert(tree);
				}
			}
			return null;
		}
		
		/**
		 * Insert a key value pair into this tree
		 * @param tree containing the key/value pair we're interested in
		 * @return Value that was previously associated with this key in the map or null if this key was not previously in the map
		 */
		public V deepGet(K key) {
			 if (key.compareTo(getKey()) == 0 ) {
				return getValue();
				
			} else if (key.compareTo(getKey()) > 0) {
				if (getRight() != null) {
					return _right.deepGet(key);
				}
			} else if (key.compareTo(getKey()) < 0) {
				if (getLeft() != null) {
					return _left.deepGet(key);
				}
			}
			return null;
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
		
		public SortedBinaryTree<K,V> getLeft() {
			return _left;
		}
		
		public SortedBinaryTree<K,V> getRight() {
			return _right;
		}
		
		/**
		 * An iterator of the trees in our map
		 */
		public Iterator<SortedBinaryTree<K,V>> iterator() {
			return getItems().iterator();
		}
		
		/**
		 * A flattening function that returns an arraylist of trees
		 * @returnAn Arraylist of trees in this tree 
		 */
		public ArrayList<SortedBinaryTree<K,V>> getItems() {
			ArrayList<SortedBinaryTree<K,V>> keys = new ArrayList<SortedBinaryTree<K,V>>();
			keys.add(this);
			
			if (_left != null)
				keys.addAll(_left.getItems());
			
			if (_right != null)
				keys.addAll(_right.getItems());
		
			return keys;
		}
	}
	
	/**
	 * Function that rebuilds the key ArrayList by iterating through the tree
	 */
	private void makeKeys() {
		keys.clear();
		int i;
		for (i=0; i<_len; i++) {
			if (_thisListOfLists[i] != null) {
				Iterator<SortedBinaryTree<K,V>> items = _thisListOfLists[i].iterator();
				while (items.hasNext()) {
					keys.add(items.next().getKey());
				}
			}
		}
	}
	
	/**
	 * Inner class for iterating through the keys 
	 * @param <T>
	 */
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
			return TreeHashMap.this.size();
		}
		
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean contains(Object o) {
			return TreeHashMap.this.containsKey(o);
		}
		
		@Override 
		public void clear() {
			TreeHashMap.this.clear();
		}



		
		
	}
	
	/**
	 * Associates a value with a key in our map
	 * @param K key
	 * @param V value
	 * @return V old value for that key or null
	 */
	@Override
	public V put(K key, V value) {
		
		int bucket = Math.abs(key.hashCode() % _len);
		_thisList = _thisListOfLists[bucket];
		SortedBinaryTree<K, V> newMap = new SortedBinaryTree<K, V>(key, value);

		if (_thisList == null) {
			_hashCode += key.hashCode();
			_size++;
			_thisListOfLists[bucket] = newMap;
			return null;
		}
		
		V returnValue = _thisList.insert(newMap);
		if (returnValue == null) {
			_hashCode += key.hashCode();
			_size++;
		}
		
		return returnValue;	

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

		if (_thisListOfLists[bucket] == null)
			return null;
		
		_thisList = _thisListOfLists[bucket];

		return _thisList.deepGet((K) key);
	}
	
	/**
	 * Method for clearing this map
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		_size = 0;		
		_thisListOfLists = new SortedBinaryTree[_len];

	}
	
	/**
	 * Returns true if our map contains this key
	 */
	@Override
	public boolean containsKey(Object key) {
		makeKeys();
		return keys.contains(key);
	}

	/**
	 * Method that returns true if our map is empty
	 */
	@Override
	public boolean isEmpty() {
		return (_size == 0);
	}

	/**
	 * Returns a keyset object that can be used to iterate through the keys in our map
	 */
	@Override
	public Set<K> keySet() {
		return new KeySet<K>();
	}

	/**
	 * Returns the size of this tree (number of mappings);
	 */
	@Override
	public int size() {
		return _size;
	}
	
	/**
	 * Tests tree equality by comparing item by item making sure each tree
	 * contains identical key value pairs. Uses get and containsKey from the map
	 * object and casting to access those methods.
	 */
	@Override
	public boolean equals(Object obj) {
		int i;
		K thisKey;
		V thisValue;
		if (!(obj instanceof Map))
			return false;
		
		Map comp = (Map) obj;
		
		if (comp.size() != size())
			return false;
		
		for (i=0; i<_len; i++) {
			if (_thisListOfLists[i] != null) {
				Iterator<SortedBinaryTree<K, V>> it = _thisListOfLists[i].iterator();
				
				while (it.hasNext()) {
					SortedBinaryTree<K,V> thisPair = it.next();
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
	
	@SuppressWarnings("unchecked")
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


	@SuppressWarnings("unchecked")
	@Override
	public Collection values() {
		throw new UnsupportedOperationException();

	}

}
