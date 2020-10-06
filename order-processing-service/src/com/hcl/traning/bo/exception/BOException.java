package com.hcl.traning.bo.exception;

import java.sql.SQLException;

/**
 * @author Manjeet Kumar
 *
 *         Jul 6, 2020
 */
public class BOException extends Exception {

	private static final long serialVersionUID = 1L;

	public BOException(SQLException e) {
		//super(e);
	}

}
