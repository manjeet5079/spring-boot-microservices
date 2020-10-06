package com.hcl.training.reactive.learnreactivespringboot.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import com.hcl.training.reactive.learnreactivespringboot.document.ItemCapped;

import reactor.core.publisher.Flux;
/**
 * @author Manjeet Kumar
 *
 *
 *  Jul 22, 2020
 */
@Repository
public interface ItemReactiveCappedRepository extends ReactiveMongoRepository<ItemCapped, String> {

	@Tailable
	Flux<ItemCapped> findItemsBy();
}
