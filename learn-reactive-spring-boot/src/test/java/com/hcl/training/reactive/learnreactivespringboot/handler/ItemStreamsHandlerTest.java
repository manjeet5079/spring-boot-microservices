package com.hcl.training.reactive.learnreactivespringboot.handler;



import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.MongoOperations.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.hcl.training.reactive.learnreactivespringboot.constants.ItemConstants;
import com.hcl.training.reactive.learnreactivespringboot.document.ItemCapped;
import com.hcl.training.reactive.learnreactivespringboot.repository.ItemReactiveCappedRepository;

import reactor.core.publisher.Flux;
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
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class ItemStreamsHandlerTest {

    @Autowired(required = true)
    ItemReactiveCappedRepository itemReactiveCappedRepository;

    @Autowired(required = true)
    MongoOperations mongoOperations;

    @Autowired(required = true)
    WebTestClient webTestClient;

    @Before
    public void setUp() {

        mongoOperations.dropCollection(ItemCapped.class);
        
        //mongoOperations.createCollection(ItemCapped.class, CollectionOptions.empty().maxDocuments(20).size(50000).capped());
        mongoOperations.createCollection(ItemCapped.class,CollectionOptions.empty().maxDocuments(20).size(5000).capped());
        
        Flux<ItemCapped> itemCappedFlux = Flux.interval(Duration.ofMillis(100))
                .map(i -> new ItemCapped(null, "Random Item " + i, (100.00 + i)))
                .take(5);

        itemReactiveCappedRepository
                .insert(itemCappedFlux)
                .doOnNext((itemCapped -> {
                    System.out.println("Inserted Item in setUp " + itemCapped);
                }))
                .blockLast();


    }

    @Test
    public void testStreamAllItems_Test(){

        Flux<ItemCapped> itemCappedFlux = webTestClient.get().uri(ItemConstants.ITEM_STREAM_FUNCTIONAL_END_POINT_V1)
                .exchange()
                .expectStatus().isOk()
                .returnResult((ItemCapped.class))
                .getResponseBody()
                .take(5);

        StepVerifier.create(itemCappedFlux)
                .expectNextCount(5)
                .thenCancel()
                .verify();

    }
}
