package com.dynamicprog.test;

import com.dynamicprog.Fibonacci;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FibonacciTest extends TestCase {


	 public void testSimpleFib() {
	        Fibonacci fib= new Fibonacci();
	       Assert.assertEquals(fib.getFibonacciSimpleVar(1),2);
	       // Assert.assertTrue(fib.getFibonacci(7) == 1);
	    }

}
