package com.hcl.traning.bo;

import java.sql.SQLException;

import com.hcl.traning.bo.exception.BOException;
import com.hcl.traning.dao.OrderDao;
import com.hcl.traning.dto.Order;

/**
 * @author Manjeet Kumar
 *
 *         Jul 6, 2020
 */
public class OrderBOImpl implements OrderBO {

	private OrderDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.traning.bo.OrderBO#placeOrder(com.hcl.traning.dto.Order)
	 */
	@Override
	public boolean placeOrder(Order order) throws BOException {
		try {
			int result = dao.create(order);
			if(result==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.traning.bo.OrderBO#cancelOrder(int)
	 */
	@Override
	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = dao.read(id);
			order.setStatus("cancelled");
			int result = dao.update(order);
			if(result==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.traning.bo.OrderBO#deleteOrder(int)
	 */
	@Override
	public boolean deleteOrder(int id) throws BOException {
		try {
			int result = dao.delete(id);
			if(result==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		
		
		return true;
	}

	/**
	 * @return the dao
	 */
	public OrderDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 *
	 */
	public void setDao(OrderDao dao) {
		this.dao = dao;
	}
}
