package com.dynamicprog;

public class Fibonacci {

	/**
	 * @param args
	 */

	static int arrTemp[] = new int[100];

	public static void main(String[] args) {
		System.out.println("Start...");

		int fibNum = 7;
		Fibonacci fibObj = new Fibonacci();
		System.out.println("Fib 1==" + fibObj.getFibonacciSimpleVar(fibNum));

		arrTemp[0] = 0;
		arrTemp[1] = 1;

		System.out.println("Fib 2==" + fibObj.getFibonacciArr(fibNum));

		System.out.println("End...");

	}

	public int getFibonacciSimpleVar(int n) {
		int fib = 1;
		int x = 0, y = 1;
		if (n == 0 || n == 1) {
			return fib;
		}
		for (int i = 2; i <= n; i++) {
			fib = x + y;
			x = y;
			y = fib;
		}

		return fib;
	}

	public int getFibonacciArr(int n) {
		int fib = 0;

		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		if (arrTemp[n] != 0)
			return arrTemp[n];//Dynamic Programming

		fib = getFibonacciArr(n - 1) + getFibonacciArr(n - 2);
		arrTemp[n] = fib;

		return fib;
	}

}
