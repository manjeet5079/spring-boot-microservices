package com.hcl.training.reactive.learnreactivespringboot.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.hcl.training.reactive.learnreactivespringboot.document.Item;

import reactor.core.publisher.Flux;

/**
 * @author Manjeet Kumar
 *
 *         Jul 20, 2020
 */
public interface ItemReactiveRepository extends ReactiveMongoRepository<Item, String> {

	Flux<Item> findByDescription(String description);
}
