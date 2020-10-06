
package com.hcl.training.reactive.learnreactivespringboot.controller;

import static org.junit.Assert.assertTrue;

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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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
public class ItemControllerTest {

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
		
		webTestClient.get().uri(ItemConstants.ITEM_END_POINT_V1)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Item.class)
		.hasSize(5);
	}

	
	@Test
	public void getAllItems_Approach2() {
		
		webTestClient.get().uri(ItemConstants.ITEM_END_POINT_V1)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Item.class)
		//.hasSize(9)
		.consumeWith((response)->{
			List<Item> responseItems = response.getResponseBody();
			responseItems.forEach((item)->{
				assertTrue(item.getId()!=null);
				
			});
		});
	}
	
	@Test
	public void getAllItems_Approach3() {
		
		Flux<Item> responseItems = webTestClient.get().uri(ItemConstants.ITEM_END_POINT_V1)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.returnResult(Item.class)
		.getResponseBody();
		
		StepVerifier.create(responseItems.log("getAllItems_Approach3::"))
		.expectNextCount(5)
		.verifyComplete();
		
	}
	
	@Test
	public void getOneItemTest() {
		
		webTestClient.get().uri(ItemConstants.ITEM_END_POINT_V1.concat("/{id}"),"ABC")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price", 111.0);
	}
	
	@Test
	public void getOneItem_NotFound_Test() {
		
		webTestClient.get().uri(ItemConstants.ITEM_END_POINT_V1.concat("/{id}"),"DEF")
        .exchange()
        .expectStatus().isNotFound();
		
	}
	
	@Test
	public void createItemTest() {
		Item item = new Item(null, "I Phone X", 999.0);
		webTestClient.post().uri(ItemConstants.ITEM_END_POINT_V1)
		.contentType(MediaType.APPLICATION_JSON)
		.body(Mono.just(item),Item.class)
		.exchange()
		.expectStatus().isCreated()
		.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.description").isEqualTo("I Phone X")
		.jsonPath("$.price").isEqualTo(999.0);
	}
	
	@Test
	public void deleteItemTest() {
		
		webTestClient.delete().uri(ItemConstants.ITEM_END_POINT_V1.concat("/{id}"),"ABC")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Void.class);
	}
	
	@Test
	public void updateItemTest() {
		double newPrice=129.99;
		Item item =new Item(null,"INDIA TV",newPrice);
		webTestClient.put().uri(ItemConstants.ITEM_END_POINT_V1.concat("/{id}"),"ABC")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(item), Item.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price",newPrice);
	}
	
	@Test
	public void updateItem_NotFound_Test() {
		double newPrice=129.99;
		Item item =new Item(null,"INDIA TV",newPrice);
		webTestClient.put().uri(ItemConstants.ITEM_END_POINT_V1.concat("/{id}"),"DEF")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(item), Item.class)
        .exchange()
        .expectStatus().isNotFound();
		
	}
	
	@Test
    public void runTimeException_Test(){
        webTestClient.get().uri(ItemConstants.ITEM_END_POINT_V1+"/runtimeException")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
                .isEqualTo("RuntimeException Occurred.");



    }
}
