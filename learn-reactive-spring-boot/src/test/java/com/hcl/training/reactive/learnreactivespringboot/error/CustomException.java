package com.hcl.training.reactive.learnreactivespringboot.error;

/**
 * @author Manjeet Kumar
 *
 *         Jul 18, 2020
 */
public class CustomException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public CustomException(Throwable e) {
		this.message = e.getMessage();

	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
