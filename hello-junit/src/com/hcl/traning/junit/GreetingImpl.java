package com.hcl.traning.junit;

/**
 * @author Manjeet Kumar
 *
 * Jul 3, 2020
 */
public class GreetingImpl implements Greeting {

	/* (non-Javadoc)
	 * @see com.hcl.traning.junit.Greeting#greet(java.lang.String)
	 */
	@Override
	public String greet(String name) {
		
		if(name==null || name.length()==0) {
			throw new IllegalArgumentException();
		}
		return "Hello "+name;
	}

}
