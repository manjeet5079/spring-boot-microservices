package com.hcl.training.reactive.learnreactivespringboot.filter;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 * Jul 17, 2020
 */
public class FluxFilterTest {

	List<String> names = Arrays.asList("Manjeet", "Sanjeet", "Ranjeet", "Raju");
	
	@Test
	public void filterTest() {
		
		Flux<String> namesFlux = Flux.fromIterable(names)//"Manjeet", "Sanjeet", "Ranjeet", "Raju"
				.filter(s->s.startsWith("M"))//Manjeet
				.log();
		
		StepVerifier.create(namesFlux)
		.expectNext("Manjeet")
		.verifyComplete();
	}
	
	@Test
	public void filterTestLength() {
		
		Flux<String> namesFlux = Flux.fromIterable(names)//"Manjeet", "Sanjeet", "Ranjeet", "Raju"
				.filter(s->s.length()>5)//Manjeet
				.log();
		
		StepVerifier.create(namesFlux)
				.expectNext("Manjeet", "Sanjeet", "Ranjeet").verifyComplete();
	}
}
