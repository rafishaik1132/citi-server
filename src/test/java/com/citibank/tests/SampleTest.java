package com.citibank.tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SampleTest {
	
	String sample = "welcome";
	
	@Test
	public void testMessage() {
		System.out.println("Inside junit");
		assertEquals(sample,"welcome");
		assertEquals(10,10);
	}

}
