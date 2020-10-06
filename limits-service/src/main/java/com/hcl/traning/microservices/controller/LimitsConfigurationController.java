package com.hcl.traning.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.microservices.Configration;
import com.hcl.traning.microservices.bean.LimitConfigration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Manjeet Kumar
 *
 *         Jun 29, 2020
 */

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configration configration;

	@GetMapping("/limits")
	public LimitConfigration retrieveLimitsFromConfigrations() {
		return new LimitConfigration(configration.getMinimum(), configration.getMaximum());
	}

	@GetMapping("/fault-tolerance-limits")
	@HystrixCommand(fallbackMethod = "fallBackRetriveConfigration")
	public LimitConfigration retrieveConfigration() {
		throw new RuntimeException("not avalable");
	}

	public LimitConfigration fallBackRetriveConfigration(){
		return new LimitConfigration(9,999);
	}
}
