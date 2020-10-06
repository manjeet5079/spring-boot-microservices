package com.hcl.traning.mockito.spy;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Manjeet Kumar
 *
 *         Jul 7, 2020
 */
public class ListTest {

	//@Spy
	@Mock
	List<String> list = new ArrayList<>();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		Mockito.when(list.get(0)).thenReturn("rambo");
		Mockito.when(list.size()).thenCallRealMethod();
		assertSame(3, list.size());

	}

}
