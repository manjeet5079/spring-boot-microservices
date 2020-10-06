package com.hcl.traning.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootIn10StepsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootIn10StepsApplication.class, args);
	
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println("name -> "+name);
		}
	}

}
