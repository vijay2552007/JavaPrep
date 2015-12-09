package com.dynamicprog;

import java.util.ArrayList;
import java.util.Iterator;

public class LongComSubSeq {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		char rowArr[] = new char[] { 'a', 'c', 'b', 'x', 'e', 'f', 'c', 'x',
				'e', 'f', 'd' };// Input Array row
		char colArr[] = new char[] { 'a', 'z', 'b', 'c', 'e', 'd', 'd', 'x',
				'e', 'd', 'f' };// Input Array col

		processLCS(rowArr, colArr);

	}

	private static void processLCS(char[] row, char[] col) {

		// By default all values are initialized to 0.
		int tableArr[][] = new int[col.length + 1][row.length + 1];

		// To store the max LCS value
		int maxLCSValue = 0;

		// For each col, process all rows
		for (int i = 0; i < col.length; i++) {
			for (int j = 0; j < row.length; j++) {
				if (col[i] == row[j]) {
					// Incrementing LCS Value
					tableArr[i + 1][j + 1] = tableArr[i][j] + 1;
				} else {
					// Just lookup from max value of either of the
					// adjacent left row or up column
					tableArr[i + 1][j + 1] = Math.max(tableArr[i + 1][j],
							tableArr[i][j + 1]);
				}

				if (tableArr[i + 1][j + 1] > maxLCSValue)
					maxLCSValue = tableArr[i + 1][j + 1];// Always store the
				// computed max LCS
				// Value into this
				// variable

			}

		}

		// Reverse track the Table array to get the subsequence
		StringBuffer sb = new StringBuffer();
		for (int i = col.length, j = row.length; i != 0 && j != 0;) {
			if (tableArr[i][j] == tableArr[i][j - 1]) {//if above value is equal
				j--;
			} else if (tableArr[i][j] == tableArr[i - 1][j]) {//if left adjacent value is equal
				i--;
			} else {
				sb.append(col[i - 1]);//if above 2 conditions doesn't meet, assume value came diagonally. i.e. new char added to subsequence...
				i--;
				j--;
			}
		}
		sb = sb.reverse();


		//=============================Print everything to console from here====================================================
		// Print the tableArr
		System.out.print("LCS Matrix :- ");
		for (int rowLine = 0; rowLine < tableArr.length; rowLine++) {
			System.out.print("\n");
			for (int element = 0; element < tableArr[rowLine].length; element++) {
				System.out.print(" " + tableArr[rowLine][element]);
			}
		}

		// Print the maxLCSValue
		System.out.println("\n\nMax LCS Value  = " + maxLCSValue);

		// Print the SubSequence
		System.out.println("\n\nSub Sequence  = " + sb);

	}

}
