/**
 * 
 */
package com.hcl.training.reactive.learnreactivespringboot.initialize;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hcl.training.reactive.learnreactivespringboot.document.Item;
import com.hcl.training.reactive.learnreactivespringboot.repository.ItemReactiveRepository;

import reactor.core.publisher.Flux;

/**
 * @author Manjeet Kumar
 *
 *
 *  Jul 21, 2020
 */
@Component
@Profile("test")
public class ItemDataInitializer implements CommandLineRunner{

	@Autowired
	ItemReactiveRepository itemReactiveRepository;
	
	@Override
	public void run(String... args) throws Exception {
		initialDataSetup();
	}

	public List<Item> data() {
		return Arrays.asList(
				new Item(null, "Sunsung TV", 400.0), 
				new Item(null, "LG TV", 420.0),
				new Item(null, "Apple TV", 299.0), 
				new Item(null, "Boat TV", 149.0),
				new Item("ABC", "INDIA TV", 111.0));
	}
	private void initialDataSetup() {
		itemReactiveRepository.deleteAll()
		.thenMany(Flux.fromIterable(data()))
		.flatMap(itemReactiveRepository::save)
		.thenMany(itemReactiveRepository.findAll())
		.subscribe((item ->{
			System.out.println("Item installed by commandLineRunner::"+item);
		}));
	}

}
