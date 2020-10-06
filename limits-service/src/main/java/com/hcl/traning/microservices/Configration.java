package com.hcl.traning.microservices;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Manjeet Kumar
 *
 * Jun 29, 2020
 */
@Component
@ConfigurationProperties("limit-service")
public class Configration {

	private int minimum;
	private int maximum;
	
	

	public Configration() {
	}
	/**
	 * @param minimum
	 * @param maximum
	 */
	public Configration(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}
	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}
	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}
	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	
}
