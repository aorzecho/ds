package org.aorzecho.ds;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.aorzecho.ds.OrderStatisticTreeMap;
import org.junit.Test;

/**
 * Test order statistics functionality of augmented TreeMap.
 * 
 *
 * @author Arkadiusz Orzechowski <aorzecho@gmail.com>
 */
public class OrderStatisticTreeMapTest {

	static final int TEST_SIZE=1000000;

	@Test
	public void testWithRandomIntegers() {

		Random random = new Random(1);
		OrderStatisticTreeMap<Integer, Integer> tm = new OrderStatisticTreeMap<Integer, Integer>();
		ArrayList<Integer> arr = new ArrayList<Integer>(TEST_SIZE);
		
		for (int i=0; i< TEST_SIZE; i++) {
			Integer r = random.nextInt();
			while (tm.containsKey(r)) {
				r = random.nextInt();
			}
			arr.add(r);
			tm.put(r, r);
		}
		Collections.sort(arr);
		for (int i=0; i< TEST_SIZE; i++) {
			assertEquals(arr.get(i), tm.getIthKey(i+1));
		}
	}

}
