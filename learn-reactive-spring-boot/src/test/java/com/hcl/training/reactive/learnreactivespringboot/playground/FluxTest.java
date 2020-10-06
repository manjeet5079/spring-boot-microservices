package com.hcl.training.reactive.learnreactivespringboot.playground;


import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 *         Jul 17, 2020
 */
public class FluxTest {

	@Test
	public void fluxTest() {
		Flux<String> stringFlux = Flux.just("Spring","Spring Boot","Reactive Spring")
				/*.concatWith(Flux.error(new RuntimeException("Exception Occurred")))*/
				.concatWith(Flux.just("After Error"))
				.log();
		
		stringFlux.subscribe(System.out::println,
				(e)-> System.err.println("Exception is "+e),
				()-> System.out.println("Completed"));
	}

	@Test
	public void fluxtestElements_WithoutError() {
		Flux<String> stringFlux = Flux.just("Spring","Spring Boot","Reactive Spring")
				.log();
		
		StepVerifier.create(stringFlux)
		.expectNext("Spring")
		.expectNext("Spring Boot")
		.expectNext("Reactive Spring");
		//.verifyComplete();
	}
	
	@Test
	public void fluxtestElements_WithError() {
		Flux<String> stringFlux = Flux.just("Spring","Spring Boot","Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred")))
				.log();
		
		StepVerifier.create(stringFlux)
		.expectNext("Spring")
		.expectNext("Spring Boot")
		.expectNext("Reactive Spring")
		.expectErrorMessage("Exception Occurred")
		.verify();
	}
	
	@Test
	public void fluxtestElementsCount_WithError() {
		Flux<String> stringFlux = Flux.just("Spring","Spring Boot","Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred")))
				.log();
		
		StepVerifier.create(stringFlux)
		.expectNextCount(3)
		.expectErrorMessage("Exception Occurred")
		.verify();
	}
	
	@Test
	public void fluxtestElements_WithError1() {
		Flux<String> stringFlux = Flux.just("Spring","Spring Boot","Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred")))
				.log();
		
		StepVerifier.create(stringFlux)
		.expectNext("Spring","Spring Boot","Reactive Spring")
		.expectErrorMessage("Exception Occurred")
		.verify();
	}
	
	
	
}
