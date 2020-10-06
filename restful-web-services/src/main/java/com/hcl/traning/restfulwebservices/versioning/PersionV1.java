package com.hcl.traning.restfulwebservices.versioning;

/**
 * @author Manjeet Kumar
 *
 * Jun 28, 2020
 */
public class PersionV1 {

	private String name;

	/**
	 * 
	 */
	public PersionV1() {
	}

	/**
	 * @param name
	 */
	public PersionV1(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
