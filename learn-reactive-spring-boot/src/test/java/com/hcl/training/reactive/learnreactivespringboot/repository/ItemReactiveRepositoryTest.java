package com.hcl.training.reactive.learnreactivespringboot.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.training.reactive.learnreactivespringboot.document.Item;
import com.hcl.training.reactive.learnreactivespringboot.repository.ItemReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Manjeet Kumar
 *
 *         Jul 20, 2020
 */

@DataMongoTest
@RunWith(SpringRunner.class)

public class ItemReactiveRepositoryTest {

	@Autowired
	private ItemReactiveRepository itemReactiveRepository;

	List<Item> itemList = Arrays.asList(
			new Item(null, "Sunsung TV", 400.0), 
			new Item(null, "LG TV", 420.0),
			new Item(null, "Apple TV", 299.0), 
			new Item(null, "Boat TV", 149.0),
			new Item("ABC", "INDIA TV", 111.0));

	@Before
	public void setUp() {
		itemReactiveRepository
		.deleteAll()
		.thenMany(Flux.fromIterable(itemList))
		.flatMap(itemReactiveRepository::save)
				.doOnNext((item -> {
					System.out.println("Inserted Item is::" + item);
				})).blockLast();
	}

	@Test
	public void getAllItemsTest() {
		StepVerifier
		.create(itemReactiveRepository.findAll())
		.expectSubscription()
		.expectNextCount(5)
		.verifyComplete();
		;
	}
	
	
	@Test
	public void getItemsByIdTest() {
		StepVerifier
		.create(itemReactiveRepository.findById("ABC"))
				.expectSubscription()
				.expectNextMatches((item -> item.getDescription().equals("INDIA TV")))
				.verifyComplete();
	}
	
	@Test
	public void findByDescriptionTest() {
		StepVerifier
		.create(itemReactiveRepository.findByDescription("INDIA TV").log("findByDescriptionTest:"))
				.expectSubscription()
				.expectNextCount(1)
				.verifyComplete();
	}
	
	@Test
	public void saveItemTest() {
		Item item =new Item(null,"Google Home mini", 500.0);
		Mono<Item> savedItem = itemReactiveRepository.save(item);
		StepVerifier.create(savedItem.log("saveItem::"))
		.expectSubscription()
		.expectNextMatches(items -> (items.getId()!=null && items.getDescription().equals("Google Home mini")))
		.verifyComplete();
	}
	
	@Test
	public void updateItemTest() {
		double newPrice=520.00;
		Flux<Item> updatedItem = itemReactiveRepository.findByDescription("LG TV")
		.map(item->{
			item.setPrice(newPrice);
			return item;
		})
		.flatMap(item->{
			return itemReactiveRepository.save(item);
		});
		
		StepVerifier.create(updatedItem)
		.expectSubscription()
		.expectNextMatches(item-> item.getPrice()==520.00)
		.verifyComplete();
	}
	
	@Test
	public void deleteById() {
		
		Mono<Void> deletedItem = itemReactiveRepository.findById("ABC")
		.map(Item::getId)
		.flatMap((id) ->{
			return itemReactiveRepository.deleteById(id);
		});
		
		StepVerifier.create(deletedItem)
		.expectSubscription()
		.verifyComplete();
		
		StepVerifier.create(itemReactiveRepository.findAll().log("New Item List::"))
		.expectSubscription()
		.expectNextCount(4)
		.verifyComplete();
		
	}
}
