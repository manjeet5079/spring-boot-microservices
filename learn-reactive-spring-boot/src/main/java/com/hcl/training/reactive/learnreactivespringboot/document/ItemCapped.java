package com.hcl.training.reactive.learnreactivespringboot.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Manjeet Kumar
 *
 *
 *  Jul 21, 2020
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCapped {

    @Id
    private String id;
    private String description;
    private Double price;
}
