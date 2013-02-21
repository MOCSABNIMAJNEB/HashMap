package hashmap;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Before;
import org.junit.Test;


public class ListHashMapTest {
	ListHashMap<String,String> testMap, testMapEquals, emptyMap, collisionMap;
	ListHashMap<Integer, Integer> integerMap;
	ListHashMap<ListHashMap<String,String>, Integer> crazyMap;
	
	@Before
	public void setUp() throws Exception {
		testMap = new ListHashMap<String, String>(100);
		emptyMap = new ListHashMap<String, String>(100);
		integerMap = new ListHashMap<Integer, Integer>(100);
		collisionMap = new ListHashMap<String, String>(1);
		crazyMap = new ListHashMap<ListHashMap<String,String>, Integer>(100);
		crazyMap.put(testMap, 1);
		crazyMap.put(emptyMap, 2);
		crazyMap.put(collisionMap, 3);
		
		testMap.put("Tom", "Jones");
		testMap.put("Dick" , "Tracey");
		
		integerMap.put(1,2);
		integerMap.put(2,4);
		collisionMap.put("dog", "leon");
		collisionMap.put("cat", "sunny");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeZero() {
		testMap = new ListHashMap<String,String>(0);
	};

	@Test
	public void testBigTree() {
		int i;
		
		for (i=0; i<10000; i++)  {
			integerMap.put(i, i*2);
		}
		
		assertEquals(46, (int)integerMap.get(23));
		assertEquals(198, (int)integerMap.get(99));
		assertEquals(1400, (int)integerMap.get(700));

			
	}
	
	@Test
	public void testCollisionTree() {
		assertEquals("sunny", collisionMap.get("cat"));
	}


	@Test
	public void testContainsKey() {
		assertTrue(testMap.containsKey("Tom"));
		assertFalse(testMap.containsKey("Bob"));
		assertFalse(testMap.containsKey(1));
		assertFalse(testMap.containsKey(collisionMap));
		assertTrue(crazyMap.containsKey(testMap));
	}

	@Test
	public void testSize() {
		assertEquals(0, emptyMap.size());
		assertEquals(2, testMap.size());

	}

	@Test
	public void testPut() {
		assertNull(testMap.put("Carmen", "Sandiego"));
		
		assertEquals(3, testMap.size());
		assertTrue(testMap.containsKey("Carmen"));
		assertEquals("Sandiego", testMap.get("Carmen"));
		assertEquals("Jones", testMap.put("Tom", "Waits"));
		assertEquals("Waits", testMap.get("Tom"));
	}

	@Test
	public void testGet() {
		assertEquals("Jones", testMap.get("Tom"));
		assertEquals("Tracey", testMap.get("Dick"));
		assertNull("Hope", testMap.get("Bob"));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(testMap.isEmpty());
		assertTrue(emptyMap.isEmpty());
		testMap.put("Tom", "Petty");
		assertEquals(2, testMap.size());
	}
	
	@Test
	public void testKeySet() {
		Set<String> testSet = testMap.keySet();
		
		assertEquals(testSet.size(), testMap.size());
		assertTrue(testSet.contains("Tom"));
		
		testSet = collisionMap.keySet();
		assertEquals(2, testSet.size());
	}
	
	@Test
	public void testClear() {
		testMap.clear();
		Set<String> testSet = testMap.keySet();
		assertEquals(0, testMap.size());
		assertTrue(testSet.isEmpty());
	}
	
	@Test
	public void testEquals() {
		testMapEquals = new ListHashMap<String,String>(100);
		testMapEquals.put("Tom", "Jones");
		testMapEquals.put("Dick" , "Tracey");
		assertTrue(testMap.equals(testMapEquals));
		assertFalse(testMap.equals(emptyMap));
		assertFalse(testMap.equals(integerMap));
	}

}
