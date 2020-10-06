package com.hcl.training.reactive.learnreactivespringboot.time;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 * Jul 18, 2020
 */
public class FluxAndMonoTimeTest {
	
	@Test
	public void infiniteSequence() throws InterruptedException {
		Flux<Long> infiniteFlux = Flux.interval(Duration.ofMillis(200))
				.log();
		
		infiniteFlux.subscribe((element)-> System.out.println("Value is::"+element));
		
		Thread.sleep(3000);
	}

	
	@Test
	public void infiniteSequenceTest() {
		
		Flux<Long> infiniteFlux = Flux.interval(Duration.ofMillis(100))
				.take(3)
				.log();
		
		StepVerifier.create(infiniteFlux)
		.expectSubscription()
		.expectNext(0L,1L,2L)
		.verifyComplete();
		
	}
	
	@Test
	public void infiniteSequenceMap() {
		
		Flux<Integer> infiniteFlux = Flux.interval(Duration.ofMillis(100))
				.map(l-> new Integer(l.intValue()))
				.take(3)
				.log();
		
		StepVerifier.create(infiniteFlux)
		.expectSubscription()
		.expectNext(0,1,2)
		.verifyComplete();
		
	}
	
	@Test
	public void infiniteSequenceMap_WithDelay() {
		
		Flux<Integer> infiniteFlux = Flux.interval(Duration.ofMillis(100))
				.delayElements(Duration.ofSeconds(1))
				.map(l-> new Integer(l.intValue()))
				.take(3)
				.log();
		
		StepVerifier.create(infiniteFlux)
		.expectSubscription()
		.expectNext(0,1,2)
		.verifyComplete();
		
	}
}
