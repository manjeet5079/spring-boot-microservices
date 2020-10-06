package com.hcl.training.reactive.learnreactivespringboot.combine;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;



/**
 * @author Manjeet Kumar
 *
 * Jul 18, 2020
 */
public class FluxAndMonoCombineTest {
	
	@Test
	public void combineUsingMerge() {
		
		Flux<String> flux1 = Flux.just("A","B","C");
		Flux<String> flux2 = Flux.just("D","E","F");
		Flux<String> mergedFlux = Flux.merge(flux1,flux2);
		
		StepVerifier.create(mergedFlux)
		.expectSubscription()
		.expectNext("A","B","C","D","E","F")
		.expectComplete();
	}
	
	@Test
	public void combineUsingMerge_WithDelay() {
		
		Flux<String> flux1 = Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));
		Flux<String> mergedFlux = Flux.merge(flux1,flux2);
		
		StepVerifier.create(mergedFlux)
		.expectSubscription()
		.expectNextCount(6)
		//.expectNext("A","B","C","D","E","F")
		.expectComplete();
	}
	
	@Test
	public void combineUsing_Concat() {
		
		Flux<String> flux1 = Flux.just("A","B","C");
		Flux<String> flux2 = Flux.just("D","E","F");
		Flux<String> mergedFlux = Flux.concat(flux1,flux2);
		
		StepVerifier.create(mergedFlux)
		.expectSubscription()
		.expectNext("A","B","C","D","E","F")
		.expectComplete();
	}
	
	@Test
	public void combineUsing_Concat_WithDelay() {
		
		Flux<String> flux1 = Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));
		Flux<String> mergedFlux = Flux.concat(flux1,flux2);
		
		StepVerifier.create(mergedFlux)
		.expectSubscription()
		.expectNext("A","B","C","D","E","F")
		.expectComplete();
	}
	
	@Test
	public void combineUsing_Zip() {
		
		Flux<String> flux1 = Flux.just("A","B","C");
		Flux<String> flux2 = Flux.just("D","E","F");
		Flux<String> mergedFlux = Flux.zip(flux1,flux2,(t1,t2)->{
			return t1.concat(t2);
		});
		
		StepVerifier.create(mergedFlux)
		.expectSubscription()
		.expectNext("AD","BE","CF")
		.expectComplete();
	}

}
