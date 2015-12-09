package com.dynamicprog;

public class LongIncSeq {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int inputArr[] = new int[] { -9, -4, 8, -2, 1, 4, -1, 3, 4, 9, 14, -7,
				12, 16, 21, 20, 19 };//Input Array

		processLIS(inputArr);

	}

	private static void processLIS(int[] inputArr) {

		int[] seqList = new int[inputArr.length];//Stores LIS
		int[] parenList = new int[inputArr.length];//Stores Parent Index

		int maxSeqNum = 1;

		// Initialize seqList with all 1's for LIS and -1's for ParentIdx
		for (int i = 0; i < seqList.length; i++) {
			seqList[i] = 1;
			parenList[i] = -1;
		}

		for (int j = 1; j < seqList.length; j++) {//Iterate from 1 index to end of input list
			int parenTemp = 0;//Have this Temp variable initialized to 0 between outer and inner loop. Used to hold Temp parent value while inner loop is iterating

			for (int i = 0; i < j; i++) { //Iterate from 0th index to 1 lesser of outer loop

				if (inputArr[i] < inputArr[j]) {//If current outer loop value is greater than inner loop value
					seqList[j] = Math.max(seqList[j], seqList[i] + 1); // Store LIS length using max [Outer loop LIS value, inner loop's LIS value +1]


					//This is to find the parent Index - Start
					if (seqList[parenTemp] < seqList[i]) { // If the last LIS value calculated was lesser than the current inner loop's LIS value
						parenTemp = i;//This makes sure parenTemp variable always holds the largest LIS value that was calculated during the inner loop iteration...
						parenList[j] = i;
					} else {//The ParenTemp variable holds the correct Parent Index with the largest LIS Value...
						parenList[j] = parenTemp;
					}//This is to find the parent Index - End


					if (maxSeqNum < seqList[j]) //To determine the max length of LIS
						maxSeqNum = seqList[j];

				}

			}

		}

		System.out.println("MaxSeq==" + maxSeqNum); //Print Max LIS

		System.out.print("Input== ");
		for (int i = 0; i < inputArr.length; i++) {
			System.out.print(inputArr[i] + ",");//Print Max Input Array
		}

		System.out.println("\n=================");

		System.out.print("LIS== ");
		for (int i = 0; i < seqList.length; i++) {
			System.out.print(seqList[i] + ",");//Print Max LIS Array
		}

		System.out.println("\n=================");

		System.out.print("ParentIdx== ");
		for (int i = 0; i < parenList.length; i++) {
			System.out.print(parenList[i] + ",");//Print Max Parent Index Array
		}

	}

}
