package com.hcl.training.reactive.learnreactivespringboot.transform;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 *         Jul 18, 2020
 */
public class TransformFluxTest {

	List<String> names = Arrays.asList("Manjeet", "Sanjeet", "Ranjeet", "Raju");

	@Test
	public void transformUsingMap() {

		Flux<String> fluxNames = Flux.fromIterable(names)
				.map(s -> s.toUpperCase())
				.log();

		StepVerifier.create(fluxNames)
		.expectNext("MANJEET", "SANJEET", "RANJEET", "RAJU")
		.verifyComplete();

	}

	@Test
	public void transformUsingMap_Length() {

		Flux<Integer> fluxLength = Flux.fromIterable(names).map(s -> s.length()).log();

		StepVerifier.create(fluxLength)
		.expectNext(7,7,7,4)
		.verifyComplete();

	}

	@Test
	public void transformUsingMap_Length_Repeat() {

		Flux<Integer> fluxLength = Flux.fromIterable(names).map(s -> s.length()).repeat(1).log();

		StepVerifier.create(fluxLength)
		.expectNext(7, 7, 7, 4, 7, 7, 7, 4)
		.verifyComplete();

	}

	@Test
	public void transformUsingMap_Filter() {

		Flux<String> fluxResult = Flux.fromIterable(names).filter(s -> s.length() > 4).map(s -> s.toUpperCase()).log();

		StepVerifier.create(fluxResult)
		.expectNext("MANJEET", "SANJEET", "RANJEET")
		.verifyComplete();

	}

	@Test
	public void transformUsingFlatMap() {

		Flux<String> fluxResult = Flux.fromIterable(Arrays.asList("A", "b","C","D","E"))
				.flatMap(s -> {

			return Flux.fromIterable(ConvertToList(s));
		}).log();

		System.out.println("fluxResult::"+fluxResult);
		StepVerifier.create(fluxResult)
		.expectNextCount(10)
		.verifyComplete();

	}

	/**
	 * @param s
	 * @return
	 * @throws InterruptedException
	 */
	private List<String> ConvertToList(String s) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return Arrays.asList(s, "newValue");
	}

	
	/*@Test
	public void transformUsingFlatMap_Usingparalel() {
		Flux<String> fluxResult =Flux.fromIterable(Arrays.asList("A", "b", "C", "D", "E"))
		 .window(2)
		 .flatMap((s)->s.map(this::ConvertToList).subscribeOn(parallel()))
		 .flatMap(s->Flux.fromIterable(s))
		 .log();
		 
		 StepVerifier.create(fluxResult)
		 .expectNextCount(23)
		 .verifyComplete();
		
	}*/
	
	
	/*@Test
	public void transformUsingFlatMap_Usingparalel_Maintain_Order() {
		Flux<String> fluxResult =Flux.fromIterable(Arrays.asList("A", "b", "C", "D", "E"))
		 .window(2)
		 .concatMap((s)->s.map(this::ConvertToList).subscribeOn(parallel()))
		 
		 .flatMapSequential((s)->s.map(this::ConvertToList).subscribeOn(parallel()))
		 .flatMap(s->Flux.fromIterable(s))
		 .log();
		 
		 StepVerifier.create(fluxResult)
		 .expectNextCount(23)
		 .verifyComplete();
		
	}*/
}
