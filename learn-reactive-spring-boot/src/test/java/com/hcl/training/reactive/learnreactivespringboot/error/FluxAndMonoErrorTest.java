package com.hcl.training.reactive.learnreactivespringboot.error;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 * Jul 18, 2020
 */
public class FluxAndMonoErrorTest {
	
	@Test
	public void fluxErrorHandling() {
		
		Flux<String> stringFlux = Flux.just("A","B","C")
		.concatWith(Flux.error(new RuntimeException("Exception Occurred !")))
		.concatWith(Flux.just("D"))
		.onErrorResume((e)->{
			System.out.println("Exception::"+e);
			return Flux.just("default","default1");
		});
		
		
		StepVerifier.create(stringFlux.log())
		.expectSubscription()
		.expectNext("A","B","C")
		//.expectError(RuntimeException.class)
		//.verify();
		.expectNext("default","default1")
		.verifyComplete();
	}
	
	@Test
	public void fluxErrorHandling_OnErrorReturn() {
		
		Flux<String> stringFlux = Flux.just("A","B","C")
		.concatWith(Flux.error(new RuntimeException("Exception Occurred !")))
		.concatWith(Flux.just("D"))
		.onErrorReturn("default");		
				
		
		StepVerifier.create(stringFlux.log())
		.expectSubscription()
		.expectNext("A","B","C")
		.expectNext("default")
		.verifyComplete();
	}
	
	@Test
	public void fluxErrorHandling_OnErrorMap() {
		
		Flux<String> stringFlux = Flux.just("A","B","C")
		.concatWith(Flux.error(new RuntimeException("Exception Occurred !")))
		.concatWith(Flux.just("D"))
		.onErrorMap((e)-> new CustomException(e));		
				
		
		StepVerifier.create(stringFlux.log())
		.expectSubscription()
		.expectNext("A","B","C")
		.expectError(CustomException.class)
		.verify();
	}
	
	@Test
	public void fluxErrorHandling_OnErrorMap_Retry() {
		
		Flux<String> stringFlux = Flux.just("A","B","C")
		.concatWith(Flux.error(new RuntimeException("Exception Occurred !")))
		.concatWith(Flux.just("D"))
		.onErrorMap((e)-> new CustomException(e))
		.retry(2);		
				
		
		StepVerifier.create(stringFlux.log())
		.expectSubscription()
		.expectNext("A","B","C")
		.expectNext("A","B","C")
		.expectNext("A","B","C")
		.expectError(CustomException.class)
		.verify();
	}
	
	@Test
	public void fluxErrorHandling_OnErrorMap_Retry_Backoff() {
		
		
		Flux<String> stringFlux = Flux.just("A","B","C")
		.concatWith(Flux.error(new RuntimeException("Exception Occurred !")))
		.concatWith(Flux.just("D"))
		.onErrorMap((e)-> new CustomException(e))
		.retryBackoff(2, Duration.ofSeconds(5));		
				
		
		StepVerifier.create(stringFlux.log())
		.expectSubscription()
		.expectNext("A","B","C")
		.expectNext("A","B","C")
		.expectNext("A","B","C")
		.expectError(IllegalStateException.class)
		.verify();
	}

}
