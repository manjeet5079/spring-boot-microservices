package com.hcl.training.reactive.learnreactivespringboot.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.hcl.training.reactive.learnreactivespringboot.handler.SampleHandlerFunction;

/**
 * @author Manjeet Kumar
 *
 * Jul 19, 2020
 */
@Configuration
public class RouterFunctionConfig {

	
	@Bean
	public RouterFunction<ServerResponse> route(SampleHandlerFunction sampleHandlerFunction){
		
		return RouterFunctions
				.route((GET("/function/flux").and(accept(MediaType.APPLICATION_JSON))),sampleHandlerFunction::flux)
				.andRoute((GET("/function/mono").and(accept(MediaType.APPLICATION_JSON))),sampleHandlerFunction::mono);
	}
}
