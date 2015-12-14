package com.dynamicprog;

import java.util.HashMap;
import java.util.Iterator;

public class KnapSackRecursive {

	public static void main(String[] args) {
		int val[] = { 22, 20, 15, 30, 24, 54, 21, 32, 18, 25, 78, 34, 87, 90, 34 };
		int wt[] = { 4, 2, 3, 5, 5, 6, 9, 7, 8, 10, 3, 4, 7, 9, 7 };

		System.out.println("\n\nRecursive MaxValue for KnapSack == " + recursiveMaxValue(wt, val, 30));

	}

	// Override the key Object for HashMap so that equals and Hashcode are
	// properly implemented...
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

	// For BackTracking...
	// Start from the RootNode...
	// Left Node appear when the item is selected
	// Right Node appear when not selected...
	// Call recursively and cross compare the largest maxValue until you reach
	// the bottom of the node tree...
	private static class Node {
		Node left = null;
		Node right = null;

		int maxValue = 0;
		int weight = 0;
	}

	
	
	
	
	private static int recursiveMaxValue(int[] weights, int[] values, int w) {

		HashMap<Index, Node> mpNode = new HashMap<Index, Node>();
		Node rootNode = new Node();
		rootNode.weight = weights[0];
		Node nd = recursiveMaxValuePerform(weights, values, w, values.length, 0, rootNode, mpNode);
		printSeq(rootNode);
		return nd.maxValue;

	}

	
	
	//Do Recursion on the given Items by selecting and not selecting.
	//Built a Node tree while doimg this and store it in HashMap as Node object
		private static Node recursiveMaxValuePerform(int[] weightsArr, int[] valuesArr, int remainingWt, int remainingItems,
				int currentItem,Node nd, HashMap mp) {

			// final return or recursive breaker here
			if (remainingWt <= 0 || remainingItems <= 0)
				return new Node();

			
			Index key = new Index();
			key.remainingWt = remainingWt;
			key.remainingItems = remainingItems;

			// Memoization part here
			if (mp.containsKey(key))
				return (Node) mp.get(key);

			
			int maxValue = 0;
			if (remainingWt >= weightsArr[currentItem]) {
				Node leftNodeBySelecting = recursiveMaxValuePerform(weightsArr, valuesArr,
						remainingWt - weightsArr[currentItem], remainingItems - 1, currentItem + 1,new Node(), mp);
				
				Node rightNodeByNotSelecting = recursiveMaxValuePerform(weightsArr, valuesArr, remainingWt, remainingItems - 1, currentItem + 1,
						new Node(),mp);
				
				
				if(leftNodeBySelecting.maxValue + valuesArr[currentItem] > rightNodeByNotSelecting.maxValue)
				{
					nd.maxValue = valuesArr[currentItem] + leftNodeBySelecting.maxValue;
					nd.left = leftNodeBySelecting;//Left Node always formed by Selecting Item
				}
				else
				{
					nd.maxValue = rightNodeByNotSelecting.maxValue;
					nd.right = rightNodeByNotSelecting;
				}
				
				
			} else {
				
				Node rightNodeByNotSelecting = recursiveMaxValuePerform(weightsArr, valuesArr, remainingWt, remainingItems - 1, currentItem + 1,
						new Node(),mp);
				nd.maxValue = rightNodeByNotSelecting.maxValue;
				nd.right = rightNodeByNotSelecting;

				
			}			
			
			//Store key value pair so that can be used later..Dynamic programming applied here..
			nd.weight = weightsArr[currentItem];//Record current weight for node so that can be printed later...
			mp.put(key, nd);

			return nd;
		}
		
		

	// This method will do DFS[i.e. Depth First Search] on tree node by using recursion and will print
	// values only if max value is on Left node i.e. Item is picked...
	private static void printSeq(Node nd) {
		if (nd.right == null) {
			if (nd.left == null) {
				// Do Nothing
				return;
			} else {
				System.out.print(nd.weight + ",");// As left node selected, print
													// it on screen...
				printSeq(nd.left);
				return;
			}
		}

		if (nd.left == null) {
			if (nd.right == null) {
				// Do Nothing
				return;
			} else {
				printSeq(nd.right);
				return;
			}
		}

		if (nd.left.maxValue  > nd.right.maxValue) {
			System.out.print(nd.weight + ",");// As left node selected, print it
												// on screen...
			printSeq(nd.left);

		} else {
			printSeq(nd.right);
		}

	}

}