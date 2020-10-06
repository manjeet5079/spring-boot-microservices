package com.hcl.traning.restfulwebservices.versioning;

/**
 * @author Manjeet Kumar
 *
 * Jun 28, 2020
 */
public class PersonV2 {

	private Name name;



	/**
	 * @param name
	 */
	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}
	
	
	
}
