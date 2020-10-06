/**
 * 
 */
package com.hcl.training.reactive.learnreactivespringboot.handler;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.hcl.training.reactive.learnreactivespringboot.constants.ItemConstants;
import com.hcl.training.reactive.learnreactivespringboot.document.Item;
import com.hcl.training.reactive.learnreactivespringboot.repository.ItemReactiveRepository;

import reactor.core.publisher.Flux;

/**
 * @author Manjeet Kumar
 *
 *
 *  Jul 22, 2020
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public class ItemHandlerTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired(required = true)
	ItemReactiveRepository itemReactiveRepository;
	
	
	public List<Item> data() {
		return Arrays.asList(
				new Item(null, "Sunsung TV", 400.0), 
				new Item(null, "LG TV", 420.0),
				new Item(null, "Apple TV", 299.0), 
				new Item(null, "Boat TV", 149.0),
				new Item("ABC", "INDIA TV", 111.0));
	} 
	
	
	@Before
	public void setup() {
		
		itemReactiveRepository.deleteAll()
		.thenMany(Flux.fromIterable(data()))
		.flatMap(itemReactiveRepository::save)
		.doOnNext((item ->{
			System.out.println("Item saved into Mongodb is::::"+item);
		})).blockLast();;
	}
	/**
	 * Test method for {@link com.hcl.training.reactive.learnreactivespringboot.controller.ItemController#getAllItems()}.
	 */
	@Test
	public void getAllItemsTest() {
		
		webTestClient.get().uri(ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Item.class)
		.hasSize(9);
	}
	
	@Test
	public void getOneItemTest() {
		
		webTestClient.get().uri(ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1.concat("{/id}"),"ABC")
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Item.class)
		.hasSize(5);
	}
	
	@Test
	public void getOneItem_NotFound_Test() {
		
		webTestClient.get().uri(ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1.concat("{/id}"),"DEF")
		.exchange()
		.expectStatus().isNotFound();
		
	}

}
