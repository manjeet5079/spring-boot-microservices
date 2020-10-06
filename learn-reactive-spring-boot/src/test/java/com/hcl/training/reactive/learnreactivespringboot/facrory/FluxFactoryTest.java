package com.hcl.training.reactive.learnreactivespringboot.facrory;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 *         Jul 17, 2020
 */
public class FluxFactoryTest {

	List<String> names = Arrays.asList("Manjeet", "Sanjeet", "Ranjeet", "Raju");

	@Test
	public void fluxUsingIterable() {

		Flux<String> namesFlux = Flux.fromIterable(names);

		StepVerifier.create(namesFlux).expectNext("Manjeet", "Sanjeet", "Ranjeet", "Raju").verifyComplete();

	}

	@Test
	public void fluxUsingArray() {

		String[] names = new String[] { "Manjeet", "Sanjeet", "Ranjeet", "Raju" };
		Flux<String> namesFlux = Flux.fromArray(names);

		StepVerifier.create(namesFlux).expectNext("Manjeet", "Sanjeet", "Ranjeet", "Raju").verifyComplete();

	}

	@Test
	public void fluxUsingStream() {

		Flux<String> namesStream = Flux.fromStream(names.stream());

		StepVerifier.create(namesStream).expectNext("Manjeet", "Sanjeet", "Ranjeet", "Raju").verifyComplete();
	}

	@Test
	public void fluxUsingRange() {

		Flux<Integer> fluxInteger = Flux.range(1, 9);

		StepVerifier.create(fluxInteger).expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9).verifyComplete();
	}
}
