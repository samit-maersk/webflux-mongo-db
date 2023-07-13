package com.example.webfluxmongodb.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class EventRouters {
    @Bean
    RouterFunction routerFunction(EventHandlers eventHandlers) {
        return RouterFunctions
                .route()
                .path("/event", builder -> builder
                        .GET("", eventHandlers::get)
                        .GET("/{id}", eventHandlers::getById)
                        .POST("",eventHandlers::post)
                        .PUT("/{id}", eventHandlers::put)
                        .DELETE("/{id}", eventHandlers::delete))
                .path("/example",builder -> builder
                        .GET("/query", eventHandlers::exampleQuery)
                        .GET("/aggregation", eventHandlers::exampleAggregation))
                .build();
    }
}
