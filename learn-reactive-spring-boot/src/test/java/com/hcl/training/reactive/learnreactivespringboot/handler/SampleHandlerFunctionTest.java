package com.hcl.training.reactive.learnreactivespringboot.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 * Jul 19, 2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class SampleHandlerFunctionTest {

	@Autowired
	WebTestClient webTestClient;
	/**
	 * Test method for {@link com.hcl.training.reactive.learnreactivespringboot.handler.SampleHandlerFunction#flux(org.springframework.web.reactive.function.server.ServerRequest)}.
	 */
	@Test
	public void testFlux_Approach1() {
		Flux<Integer> integerFlux = webTestClient.get().uri("/function/flux")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.returnResult(Integer.class)
		.getResponseBody();
		
		StepVerifier.create(integerFlux)
		.expectSubscription()
		.expectNext(1)
		.expectNext(2)
		.expectNext(3)
		.expectNext(4)
		.verifyComplete();
	}

	@Test
	public void mono() {
		
		Integer expectedValue = new Integer(1);
		webTestClient
		 .get()
		 .uri("/function/mono")
		 .accept(MediaType.APPLICATION_JSON_UTF8)
		 .exchange()
		 .expectStatus().isOk()
		 .expectBody(Integer.class)
		 .consumeWith((response->{
			 assertEquals(expectedValue, response.getResponseBody());
		 }));
	}
	
}
