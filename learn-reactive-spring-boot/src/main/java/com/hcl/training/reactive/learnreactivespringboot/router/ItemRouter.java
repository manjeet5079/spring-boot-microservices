package com.hcl.training.reactive.learnreactivespringboot.router;

import static com.hcl.training.reactive.learnreactivespringboot.constants.ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1;
import static com.hcl.training.reactive.learnreactivespringboot.constants.ItemConstants.ITEM_STREAM_FUNCTIONAL_END_POINT_V1;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import com.hcl.training.reactive.learnreactivespringboot.handler.ItemHandler;

/**
 * @author Manjeet Kumar
 *
 *
 *         Jul 22, 2020
 */

@Configuration
public class ItemRouter {

	/*
	 * @Bean public RouterFunction<ServerResponse> itemsRoute(ItemHandler
	 * itemHandler){
	 * 
	 * return RouterFunctions
	 * .route(GET(ITEM_FUNCTIONAL_END_POINT_V1).and(accept(MediaType.
	 * APPLICATION_JSON)) ,itemHandler::getAllItems)
	 * .andRoute(GET(ITEM_FUNCTIONAL_END_POINT_V1+"{/id}").and(accept(MediaType.
	 * APPLICATION_JSON)) ,itemHandler::getOneItem)
	 * .andRoute(POST(ITEM_FUNCTIONAL_END_POINT_V1).and(accept(MediaType.
	 * APPLICATION_JSON)) ,itemHandler::createItem)
	 * .andRoute(DELETE(ITEM_FUNCTIONAL_END_POINT_V1+"{/id}").and(accept(MediaType.
	 * APPLICATION_JSON)) ,itemHandler::deleteItem)
	 * .andRoute(PUT(ITEM_FUNCTIONAL_END_POINT_V1+"{/id}").and(accept(MediaType.
	 * APPLICATION_JSON)) ,itemHandler::updateItem); }
	 */

	@Bean
	public RouterFunction<ServerResponse> itemsRoute(ItemHandler itemsHandler) {

		return RouterFunctions
				.route(GET(ITEM_FUNCTIONAL_END_POINT_V1).and(accept(MediaType.APPLICATION_JSON)),
						itemsHandler::getAllItems)
				.andRoute(GET(ITEM_FUNCTIONAL_END_POINT_V1 + "/{id}").and(accept(MediaType.APPLICATION_JSON)),
						itemsHandler::getOneItem)
				.andRoute(POST(ITEM_FUNCTIONAL_END_POINT_V1).and(accept(MediaType.APPLICATION_JSON)),
						itemsHandler::createItem)
				.andRoute(DELETE(ITEM_FUNCTIONAL_END_POINT_V1 + "/{id}").and(accept(MediaType.APPLICATION_JSON)),
						itemsHandler::deleteItem)
				.andRoute(PUT(ITEM_FUNCTIONAL_END_POINT_V1 + "/{id}").and(accept(MediaType.APPLICATION_JSON)),
						itemsHandler::updateItem);
	}

	@Bean
	public RouterFunction<ServerResponse> errorRoute(ItemHandler itemsHandler) {
		return RouterFunctions.route(GET("/fun/runtimeexception").and(accept(MediaType.APPLICATION_JSON)), itemsHandler::itemsEx);

	}

	/*
	 * @Bean public RouterFunction<ServerResponse> itemStreamRoute(ItemHandler
	 * itemsHandler) {
	 * 
	 * return
	 * RouterFunctions.route(GET(ITEM_STREAM_FUNCTIONAL_END_POINT_V1).and(accept(
	 * MediaType.APPLICATION_JSON)), itemsHandler::itemsStream);
	 * 
	 * }
	 */

}
