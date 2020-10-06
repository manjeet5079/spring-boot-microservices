package com.hcl.training.reactive.learnreactivespringboot.facrory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 * Jul 17, 2020
 */
public class MonoFactoryTest {

	List<String> names= Arrays.asList("Manjeet","Sanjeet","Ranjeet","Raju");
	
	
	@Test
	public void monoUsingJustOrEmpty() {
		
		Mono<Object> mono = Mono.justOrEmpty(null);
		
		StepVerifier.create(mono.log())
		.verifyComplete();
		
	}
	
	
	@Test
	public void monoUsingSupplier() {
		
		Supplier<String> stringSupplier= ()-> "Manjeet";
		
		Mono<String> resultSupplier = Mono.fromSupplier(stringSupplier);
		System.out.println("stringSupplier::"+stringSupplier.get());
		StepVerifier.create(resultSupplier.log())
		.expectNext("Manjeet")
		.verifyComplete();
	}
	
	@Test
	public void fluxUsingStream() {
		
		Flux<String> namesStream = Flux.fromStream(names.stream());
		StepVerifier.create(namesStream)
		.expectNext("Manjeet","Sanjeet","Ranjeet","Raju")
		.verifyComplete();
	}
}
