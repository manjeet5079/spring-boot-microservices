package com.hcl.traning.dao;

import java.sql.SQLException;

import com.hcl.traning.dto.Order;

/**
 * @author Manjeet Kumar
 *
 *         Jul 6, 2020
 */
public interface OrderDao {

	public int create(Order order) throws SQLException;

	public Order read(int id) throws SQLException;

	public int update(Order order) throws SQLException;

	public int delete(int id) throws SQLException;;
}
