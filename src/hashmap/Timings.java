package hashmap;
/**
 * @author benjamin bascom
 * @date 2/10/2013
 * @assignment HashMaps, CIT594 Assignment 3
 */

import java.util.HashMap;
import java.util.Random;
import java.util.*;

public class Timings {
	static String strinteger;
	static ListHashMap<String, Integer> testListHashMap;
	static TreeHashMap<String, Integer> testTreeHashMap;
	static HashMap<String, Integer> testHashMap;

	public static void main() {
		int i, n;
		double before, after, diff;
		Random generator = new Random();
		n = 1000;
		
		testListHashMap = new ListHashMap<String, Integer>(1000);
		testTreeHashMap = new TreeHashMap<String, Integer>(1000);
		testHashMap = new HashMap<String, Integer>(1000);


		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to ListHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from ListHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");


		testHashMap.clear();
		testListHashMap.clear();
		testTreeHashMap.clear();

		n = 100;

		testListHashMap = new ListHashMap<String, Integer>(100);
		testTreeHashMap = new TreeHashMap<String, Integer>(100);
		testHashMap = new HashMap<String, Integer>(100);


		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to ListHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from ListHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");


		testHashMap.clear();
		testListHashMap.clear();
		testTreeHashMap.clear();
		
		n = 10;

		testListHashMap = new ListHashMap<String, Integer>(10);
		testTreeHashMap = new TreeHashMap<String, Integer>(10);
		testHashMap = new HashMap<String, Integer>(10);


		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		/*
		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to ListHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from ListHashMap with " + n + " buckets " + diff+ " nanoseconds");
		*/
		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		System.out.println("Average time it takes to add an item to TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");


		testHashMap.clear();
		testListHashMap.clear();
		testTreeHashMap.clear();
	
		n = 1;

		testListHashMap = new ListHashMap<String, Integer>(1);
		testTreeHashMap = new TreeHashMap<String, Integer>(1);
		testHashMap = new HashMap<String, Integer>(1);


		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from java.util.HashMap with " + n + " (initial) buckets " + diff+ " nanoseconds");

		/*
		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to ListHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<100000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testListHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from ListHashMap with " + n + " buckets " + diff+ " nanoseconds");
		*/
		
		diff = 0;
		for(i=0; i<1000000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.put(toGet, Integer.parseInt(toGet));
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to add an item to TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");

		diff = 0;
		for(i=0; i<1000000; i++) {
			String toGet = generator.nextInt() + "";
			before = System.nanoTime();
			testTreeHashMap.get(toGet);
			after = System.nanoTime();
			diff += (after - before);
		}
		diff = diff/i;
		System.out.println("Average time it takes to get an item from TreeHashMap with " + n + " buckets " + diff+ " nanoseconds");

		testHashMap.clear();
		testListHashMap.clear();
		testTreeHashMap.clear();
		
		
	}
	
	
	
}
