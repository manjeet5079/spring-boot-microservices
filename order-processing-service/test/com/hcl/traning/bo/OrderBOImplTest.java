package com.hcl.traning.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hcl.traning.bo.exception.BOException;
import com.hcl.traning.dao.OrderDao;
import com.hcl.traning.dto.Order;

/**
 * @author Manjeet Kumar
 *
 *         Jul 6, 2020
 */
public class OrderBOImplTest {

	@Mock
	OrderDao dao;

	private OrderBOImpl bo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}

	/**
	 * Test method for
	 * {@link com.hcl.traning.bo.OrderBOImpl#placeOrder(com.hcl.traning.dto.Order)}.
	 * 
	 * @throws SQLException
	 * @throws BOException
	 */
	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		boolean result = bo.placeOrder(order);

		assertTrue(result);
		verify(dao).create(order);
	}

	@Test
	public void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);

		assertFalse(result);
		verify(dao).create(order);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = BOException.class)
	public void placeOrder_Should_Throw_BOException() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenThrow(BOException.class);
		bo.placeOrder(order);

	}

	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {

		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);

		boolean result = bo.cancelOrder(123);
		assertTrue(result);
		verify(dao).read(123);
		verify(dao).update(order);

	}

	@Test
	public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {

		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);

		boolean result = bo.cancelOrder(123);
		assertFalse(result);
		verify(dao).read(123);
		verify(dao).update(order);

	}

	@SuppressWarnings("unchecked")
	@Test(expected = BOException.class)
	public void cancelOrder_Should_ThrowBOException_On_Read() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenThrow(BOException.class);
		bo.cancelOrder(123);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throw_A_BOException_On_Update() throws SQLException, BOException {

		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenThrow(BOException.class);
		bo.cancelOrder(123);

	}

	@Test
	public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
		when(dao.delete(123)).thenReturn(1);
		boolean result = bo.deleteOrder(123);
		assertTrue(result);
		verify(dao).delete(123);
	}
}
