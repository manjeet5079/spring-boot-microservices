package com.hcl.traning.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.restfulwebservices.versioning.Name;
import com.hcl.traning.restfulwebservices.versioning.PersionV1;
import com.hcl.traning.restfulwebservices.versioning.PersonV2;

/**
 * @author Manjeet Kumar
 *
 *         Jun 28, 2020
 */
@RestController
public class PersonVersioningController {

	@GetMapping("/V1/person")
	public PersionV1 personV1() {
		return new PersionV1("Manjeet");
	}

	@GetMapping("/V2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Manjeet","Kumar"));
	}
	
	
	@GetMapping(value="/person/param", params="version=1")
	public PersonV2 personParamV1() {
		return new PersonV2(new Name("Manjeet","Kumar"));
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 personParamV2() {
		return new PersonV2(new Name("Manjeet","Kumar"));
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV2 personHeaderV1() {
		return new PersonV2(new Name("Manjeet","Kumar"));
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersonV2 personHeaderV2() {
		return new PersonV2(new Name("Manjeet","Kumar"));
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV2 personProducesV1() {
		return new PersonV2(new Name("Manjeet","Kumar"));
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
	public PersonV2 personProducesV2() {
		return new PersonV2(new Name("Manjeet","Kumar"));
	}
}
