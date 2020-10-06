package com.hcl.training.reactive.learnreactivespringboot.publisher;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

/**
 * @author Manjeet Kumar
 *
 * Jul 18, 2020
 */
public class ColdAndHotPublisherTest {
	
	@Test
	public <T> void coldPublisherTest() throws InterruptedException {
		
		Flux<String> stringFlux = Flux.just("A","B","C","E","F")
				.delayElements(Duration.ofSeconds(1));
		
		stringFlux.subscribe(s-> System.out.println("Subscriber 1:"+s));
		
		Thread.sleep(2000);
		
		stringFlux.subscribe(s-> System.out.println("Subscriber 2:"+s));
		Thread.sleep(4000);	
		
	}
	
	@Test
	public <T> void hotPublisherTest() throws InterruptedException {
		
		Flux<String> stringFlux = Flux.just("A","B","C","E","F")
				.delayElements(Duration.ofSeconds(1));
		
		ConnectableFlux<String> connectableFlux = stringFlux.publish();
		connectableFlux.connect();
		connectableFlux.subscribe(s-> System.out.println("Subscriber 1:"+s));
		Thread.sleep(2000);
		connectableFlux.subscribe(s-> System.out.println("Subscriber 2:"+s));
		Thread.sleep(4000);	
	}
}
