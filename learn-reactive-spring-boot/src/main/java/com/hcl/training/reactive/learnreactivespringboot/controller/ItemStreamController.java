package com.hcl.training.reactive.learnreactivespringboot.controller;

import static com.hcl.training.reactive.learnreactivespringboot.constants.ItemConstants.ITEM_STREAM_END_POINT_V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.training.reactive.learnreactivespringboot.document.ItemCapped;
import com.hcl.training.reactive.learnreactivespringboot.repository.ItemReactiveCappedRepository;

import reactor.core.publisher.Flux;

/**
 * @author Manjeet Kumar
 *
 *
 *  Jul 22, 2020
 */
@RestController
public class ItemStreamController {

	@Autowired
	ItemReactiveCappedRepository itemReactiveCappedRepository;

	@GetMapping(value = ITEM_STREAM_END_POINT_V1, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<ItemCapped> getItemsStream() {

		return itemReactiveCappedRepository.findItemsBy();
	}

}
