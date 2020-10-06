package com.hcl.traning.bo;

import com.hcl.traning.bo.exception.BOException;
import com.hcl.traning.dto.Order;

/**
 * @author Manjeet Kumar
 *
 *         Jul 6, 2020
 */
public interface OrderBO {

	boolean placeOrder(Order order) throws BOException;

	boolean cancelOrder(int id) throws BOException;

	boolean deleteOrder(int id) throws BOException;
}
