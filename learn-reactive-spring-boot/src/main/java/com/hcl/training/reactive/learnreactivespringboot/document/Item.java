package com.hcl.training.reactive.learnreactivespringboot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Manjeet Kumar
 *
 *         Jul 20, 2020
 */
@Document // same as Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	@Id
	private String id;
	private String description;
	private Double price;

}
