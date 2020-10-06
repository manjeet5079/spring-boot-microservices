package com.hcl.traning.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Manjeet Kumar
 *
 *         Jul 8, 2020
 */

@RunWith(Parameterized.class)
public class CalculatorImplTest {

	private int num1;
	private int num2;
	private int expectedResults;

	/**
	 * @param num1
	 * @param num2
	 * @param expectedResults
	 */
	public CalculatorImplTest(int num1, int num2, int results) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.expectedResults = results;
	}

	
	@Parameters
	public static Collection<Integer[]> data(){
		return Arrays.asList(new Integer[][] {{-1,2,1},{1,2,3},{6,7,13}});
	}
	/**
	 * Test method for
	 * {@link com.hcl.traning.calculator.CalculatorImpl#add(int, int)}.
	 */
	@Test
	public void add_Should_Return_The_Result() {

		Calculator calculator = new CalculatorImpl();
		int result = calculator.add(num1, num2);
		assertEquals(expectedResults, result);

	}

}
