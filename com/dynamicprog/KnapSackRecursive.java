package com.dynamicprog;

import java.util.HashMap;

public class KnapSackRecursive {

	public static void main(String[] args) {
		//int val[] = { 22, 20, 15, 30, 24, 54, 21, 32, 18, 25 };
		//int wt[] = { 4, 2, 3, 5, 5, 6, 9, 7, 8, 10 };
		
		int val[] = { 2,4,6,9 };		
		int wt[] = { 2,2,4,5};

		System.out.println("Recursive MaxValue for KnapSack == " + recursiveMaxValue(wt, val, 8));

	}

	
	//Override the key Object for HashMap so that equals and Hashcode are properly implemented...
	public static class Index {
		int remainingWt = 0;
		int remainingItems = 0;

		@Override
		public boolean equals(Object arg0) {
			Index localIdx = (Index) arg0;

			if (remainingWt != localIdx.remainingWt)
				return false;
			return remainingItems == localIdx.remainingItems;

		}

		@Override
		public int hashCode() {

			return 31 * remainingWt + remainingItems;

		}

	}

	private static int recursiveMaxValue(int[] weights, int[] values, int w) {

		HashMap<Index, Integer> mp = new HashMap<Index, Integer>();

		return recursiveMaxValuePerform(weights, values, w, values.length, 0, mp);

	}

	      
	
	//This is a recursive call
	private static int recursiveMaxValuePerform(int[] weightsArr, int[] valuesArr, int remainingWt, int remainingItems,
			int currentItem, HashMap mp) {

		// final return or recursive breaker here
		if (remainingWt <= 0 || remainingItems <= 0)
			return 0;

		// Dynamic Programming part here
		Index key = new Index();
		key.remainingWt = remainingWt;
		key.remainingItems = remainingItems;

		if (mp.containsKey(key))
			return (Integer) mp.get(key);

		// Actual processing
		//Kind of confusing..but let's try to decode here...
		//Can get max value by either using the current item or not...
		//so tree will have left and right sides here passing down recursively...
		int maxValue = 0;
		if (remainingWt >= weightsArr[currentItem]) {
			maxValue = Math.max(
					valuesArr[currentItem] + recursiveMaxValuePerform(weightsArr, valuesArr,
							remainingWt - weightsArr[currentItem], remainingItems - 1, currentItem + 1, mp),
					recursiveMaxValuePerform(weightsArr, valuesArr, remainingWt, remainingItems - 1, currentItem + 1,
							mp));
		} else {

			maxValue = recursiveMaxValuePerform(weightsArr, valuesArr, remainingWt, remainingItems - 1, currentItem + 1,
					mp);
		}
		
		
		//Store key value pair so that can be used later..Dynamic programming concept applied here..
		mp.put(key, maxValue);

		return maxValue;
	}

}
