package com.hcl.training.reactive.learnreactivespringboot.playground;


import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 *         Jul 17, 2020
 */
public class MonoTest {

	@Test
	public void monoTest() {
		Mono<String> stringMono = Mono.just("Spring");
				
		StepVerifier.create(stringMono.log())
		.expectNext("Spring")
		.verifyComplete();
				
	}

	@Test
	public void monoTest_Error() {
		
		StepVerifier.create(Mono.error(new RuntimeException("Exception Occurred")).log())
		.expectError(RuntimeException.class)
		.verify();
	}	
	
	
}
