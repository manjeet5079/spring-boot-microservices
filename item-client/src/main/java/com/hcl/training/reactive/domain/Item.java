package com.hcl.training.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Manjeet Kumar
 *
 *         Jul 22, 2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	
	private String id;
	private String description;
	private Double price;

}
