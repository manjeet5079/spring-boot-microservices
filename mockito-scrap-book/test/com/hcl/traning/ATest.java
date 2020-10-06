package com.hcl.traning;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Manjeet Kumar
 *
 *         Jul 7, 2020
 */
public class ATest {

	@Mock
	B b;

	private A a;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		a = new A(b);
	}

	@Test
	public void UsesVoidMethodShouldCallTheVoidMethod() throws Exception {
		doNothing().when(b).voidMethod();
		assertSame(1, a.usesVoidMethod());
		verify(b).voidMethod();
	}

	@Test(expected = RuntimeException.class)
	public void UsesVoidMethodShouldThrowRuntimeException() throws Exception {
		doThrow(Exception.class).when(b).voidMethod();
		a.usesVoidMethod();
	}

	@Test(expected = RuntimeException.class)
	public void testConsecutiveCalls() throws Exception {
		doNothing().doThrow(Exception.class).when(b).voidMethod();
		a.usesVoidMethod();
		verify(b).voidMethod();
		a.usesVoidMethod();
	}
}
