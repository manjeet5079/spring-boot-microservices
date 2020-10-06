/**
 * 
 */
package com.hcl.training.reactive.learnreactivespringboot.controller;

import static com.hcl.training.reactive.learnreactivespringboot.constants.ItemConstants.ITEM_END_POINT_V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.training.reactive.learnreactivespringboot.document.Item;
import com.hcl.training.reactive.learnreactivespringboot.repository.ItemReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * @author Manjeet Kumar
 *
 *
 *  Jul 21, 2020
 */
@RestController
@Slf4j
public class ItemController {

	@Autowired
	ItemReactiveRepository itemReactiveRepository;
	
	/*
	 * @ExceptionHandler(RuntimeException.class) public ResponseEntity<String>
	 * handleRuntimeException(RuntimeException ex){
	 * log.error("Exception caught in handleRuntimeException ::::  {} " , ex);
	 * return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage())
	 * ; }
	 */
	
	@GetMapping(ITEM_END_POINT_V1)
	public Flux<Item> getAllItems(){
		
		return itemReactiveRepository.findAll();
	}
	
	@GetMapping(ITEM_END_POINT_V1+"/{id}")
	public Mono<ResponseEntity<Item>> getOneitem(@PathVariable String id) {
		return itemReactiveRepository.findById(id)
			.map((item)->new ResponseEntity<>(item,HttpStatus.OK))
			.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
			
	}
	
	@PostMapping(path=ITEM_END_POINT_V1)
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Item> createItem(@RequestBody Item item) {
		return itemReactiveRepository.save(item);
	}
	
	@DeleteMapping(ITEM_END_POINT_V1+"/{id}")
	public Mono<Void> deleteItem(@PathVariable String id) {
		return itemReactiveRepository.deleteById(id);
	}
	
	@PutMapping(ITEM_END_POINT_V1+"/{id}")
	public Mono<ResponseEntity<Item>> updateItem(@PathVariable String id,@RequestBody Item item) {
		return itemReactiveRepository.findById(id)
		.flatMap(currentItem->{
			currentItem.setPrice(item.getPrice());
			currentItem.setDescription(item.getDescription());
			return itemReactiveRepository.save(currentItem);		
		})
		.map(updatedItem-> new ResponseEntity<>(updatedItem, HttpStatus.OK))
		.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping(ITEM_END_POINT_V1+"/runtimeException")
    public Flux<Item> runtimeException(){

        return itemReactiveRepository.findAll()
                .concatWith(Mono.error(new RuntimeException("RuntimeException Occurred.")));
    }
}
