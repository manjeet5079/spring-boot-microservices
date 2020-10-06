package com.hcl.traning.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hcl.traning.restfulwebservices.model.SomeBean;

/**
 * @author Manjeet Kumar
 *
 * Jun 28, 2020
 */
@RestController
public class FilteringController {

	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		 SomeBean someBean = new SomeBean("value1","value2","value3");
		 SimpleBeanPropertyFilter filter= 
				 SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		 FilterProvider filters = 
				 new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		 
		 MappingJacksonValue mapplingvalue= new MappingJacksonValue(someBean);
		 mapplingvalue.setFilters(filters);
		 return mapplingvalue;
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("value1","value2","value3")
				,new SomeBean("value11","value22","value33")
				,new SomeBean("value111","value222","value333"));
		
	}
}
