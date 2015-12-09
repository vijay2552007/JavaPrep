package com.dynamicprog;

public class Palindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String inputStr = "abdgba";

		int tableMatrix[][] = performPalindromeOnStr(inputStr);

		backtrackAndPrintPalindrome( inputStr, tableMatrix);



	}


	//Build the Matrix and find the max length of Palindrome String
	private static int[][] performPalindromeOnStr(String inputStr) {

		char inputCharArr[] = inputStr.toCharArray();
		int tableMatrix[][] = new int[inputCharArr.length][inputCharArr.length];

		// Fill the matrix with all 1's
		for (int i = 0; i < inputCharArr.length; i++) {

			for (int j = 0; j < inputCharArr.length; j++) {
				tableMatrix[i][j] = 1;
			}

		}

		// Iterate from 2nd element to the end of array
		for (int len = 2; len < inputCharArr.length+1; len++) { //Length of Palidrome to be evaluated for each cycle


			//Kind of confusing here..but her's the point...
			// consider a substing where you are evaluating a substring 'ag' for the main string 'agbdba'...
			// You have to loop 5 times...
			for (int startIdx = 0 ; startIdx < inputCharArr.length-len+1;startIdx++) {

				int endIdx = startIdx + len-1;//If you start @ 0th index , the substring's last index will be from 0th index to
				//where length mentioned by len variable...


				//Special case if Substring is only 2.
						if (len == 2 && inputCharArr[startIdx] == inputCharArr[endIdx]) {
							tableMatrix[startIdx][endIdx] = 2;

						}
						//If Substring is bigger than 2 and 1st and alst idx matches...
						else if (inputCharArr[startIdx] == inputCharArr[endIdx]) {
							tableMatrix[startIdx][endIdx] = 2 + tableMatrix[startIdx+1][endIdx-1];
						}
						//If Nothing works out the simply look for max of the adjacent column/rows
						else {
							tableMatrix[startIdx][endIdx] = Math.max(tableMatrix[startIdx][endIdx-1],
									tableMatrix[startIdx+1][endIdx]);
						}

			}

		}

		System.out.println("Biggest Palidrome Size == "
				+ tableMatrix[0][inputCharArr.length - 1] + "\n");

		return tableMatrix;

	}


	//From the matrix built, backtrack and print out the palindrome string
	private static void backtrackAndPrintPalindrome(String inputStr,int[][] tableMatrix)
	{
		char inputCharArr[] = inputStr.toCharArray();
		StringBuffer  sb = new StringBuffer();

		int j = 0;
      for(int len = inputCharArr.length-1;len>1;)//Iterate till the middle of string
      {

    	  if(tableMatrix[j][len] == tableMatrix[j][len-1])

    	  {
    		  len--;
    	  }
          else if(tableMatrix[j][len] == tableMatrix[j+1][len])

    	  {
    		  j++;
    	  }
          else

    	  {
        	  sb.append(inputCharArr[len]);
        	  j++;len--;
        	  if(tableMatrix[j][len] == 1)
        	  {

        		  sb.append(inputCharArr[len]);
        		  if(sb.toString().length() == 0 || sb.toString().length() == 1)
        			  break;

        		  char[] tempArr = sb.toString().toCharArray();
        		  for(int i=tempArr.length-2;i>-1;i--)
        		  {
        			  sb.append(tempArr[i]);
        		  }

        		  break;
        	  }
    	  }


      }

      System.out.println("Palindrome String = " +sb.toString()+"\n");


   // Print the Matrix
		System.out.print("Palindrome Matrix  =  ");
		for (int rowLine = 0; rowLine < tableMatrix.length; rowLine++) {
			System.out.print("\n");
			for (int element = 0; element < tableMatrix[rowLine].length; element++) {
				System.out.print(" " + tableMatrix[rowLine][element]);
			}
		}


	}






}
