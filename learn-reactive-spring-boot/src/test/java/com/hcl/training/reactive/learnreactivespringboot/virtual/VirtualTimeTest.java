package com.hcl.training.reactive.learnreactivespringboot.virtual;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

/**
 * @author Manjeet Kumar
 *
 * Jul 18, 2020
 */
public class VirtualTimeTest {

	
	@Test
	public void testingWithoutVirtualTime() {
		
		Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1))
				.take(3);
		
		StepVerifier.create(longFlux)
		.expectSubscription()
		.expectNext(0l,1l,2l)
		.verifyComplete();
	}
	
	@Test
	public void testingWithVirtualTime() {
		
		VirtualTimeScheduler.getOrSet();
		
		Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1))
				.take(3);
		
		StepVerifier.withVirtualTime(()-> longFlux.log())
		.expectSubscription()
		.thenAwait(Duration.ofSeconds(3))
		.expectNext(0l,1l,2l)
		.verifyComplete();
	}
}
