package hashmap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class TreeHashMapTest {
	TreeHashMap<String, String> testMap, emptyMap, collisionMap, testMapEquals;
	TreeHashMap<Integer, Integer> integerMap;
	TreeHashMap.SortedBinaryTree<String,String> testTree, testChild, testChild2, testChild3;
	
	@Before
	public void setUp() {
		testMap = new TreeHashMap<String, String>(100);
		emptyMap = new TreeHashMap<String, String>(100);
		integerMap = new TreeHashMap<Integer, Integer>(100);
		collisionMap = new TreeHashMap<String, String>(1);


		testMap.put("Tom", "Jones");
		testMap.put("Dick" , "Tracey");
		integerMap.put(1,2);
		integerMap.put(2,4);
		collisionMap.put("dog", "leon");
		collisionMap.put("cat", "sunny");
		
		testTree = new TreeHashMap.SortedBinaryTree<String,String>("Kanye", "West");
		testChild = new TreeHashMap.SortedBinaryTree<String,String>("Jay", "Z");
		testChild2 = new TreeHashMap.SortedBinaryTree<String,String>("Jay", "Dilla");
		testChild3 = new TreeHashMap.SortedBinaryTree<String,String>("Lloyd", "Banks");
		testTree.insert(testChild);
		
	}
	
	@Test
	public void testSortedBinaryTreeInsert() {
		assertEquals("Kanye", testTree.getKey());
		assertEquals("Z", testChild.getValue());
		testTree.insert(testChild2);
		assertEquals("Dilla", testChild.getValue());
		assertEquals(testChild, testTree.getLeft());
		testTree.insert(testChild3);
		assertEquals(testChild3, testTree.getRight());
	}
	
	@Test
	public void testSortedBinaryTreeGetItems() {
		ArrayList<TreeHashMap.SortedBinaryTree<String,String>> items = testTree.getItems();
		assertEquals(2, items.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitializeZero() {
		testMap = new TreeHashMap<String,String>(0);
	};

	
	@Test
	public void testBigTree() {
		int i;
		
		for (i=0; i<10000; i++)  {
			integerMap.put(i, i*2);
		}
		
		assertEquals(46, (int)integerMap.get(23));
		assertEquals(198, (int)integerMap.get(99));
		assertEquals(14000, (int)integerMap.get(7000));
		assertNull(integerMap.put(5000000, 10000000));
		assertEquals(140000, (int)integerMap.get(70000));
			
	}
	
	@Test
	public void testCollisionTree() {
		assertEquals("sunny", collisionMap.get("cat"));
		assertNull(collisionMap.put("fish", "nemo"));
	}

	
	@Test
	public void testPut() {
		assertNull(testMap.put("Carmen", "Sandiego"));
		integerMap.put(3, 6);
		assertEquals(6, (int)integerMap.get(3));
		assertEquals(3, testMap.size());
		assertEquals("Jones", testMap.put("Tom", "Waits"));
		assertEquals("Waits", testMap.get("Tom"));
		assertEquals("Waits", testMap.put("Tom", "Waits"));
	}

	@Test
	public void testGet() {
		assertEquals("Jones", testMap.get("Tom"));
		assertEquals(2, (int)integerMap.get(1));
	}

	@Test
	public void testClear() {
		testMap.clear();
		assertEquals(0, testMap.size());
		assertNull(testMap.get("Dick"));
	}

	@Test
	public void testContainsKey() {
		assertTrue(testMap.containsKey("Dick"));
		assertFalse(testMap.containsKey("Bob"));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(emptyMap.isEmpty());
		assertFalse(testMap.isEmpty());
		testMap.clear();
		assertTrue(testMap.isEmpty());
	}

	@Test
	public void testKeySet() {
		Set<String> testSet = testMap.keySet();
		
		assertEquals(testSet.size(), testMap.size());
		assertTrue(testSet.contains("Tom"));
		testMap.put("Carmen", "Sandiego");
		assertTrue(testSet.contains("Carmen"));
		
		testSet = collisionMap.keySet();
		assertEquals(2, testSet.size());
	}

	@Test
	public void testSize() {
		assertEquals(0, emptyMap.size());
		assertEquals(2, testMap.size());
		testMap.put("Tom", "Petty");
		assertEquals(2, testMap.size());
	}

	@Test
	public void testEquals() {
		testMapEquals = new TreeHashMap<String,String>(100);
		testMapEquals.put("Tom", "Jones");
		testMapEquals.put("Dick" , "Tracey");
		assertTrue(testMap.equals(testMapEquals));


	}
	
}
