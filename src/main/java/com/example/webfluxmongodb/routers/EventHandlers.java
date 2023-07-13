package com.example.webfluxmongodb.routers;

import com.example.webfluxmongodb.models.Event;
import com.example.webfluxmongodb.services.EventServices;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record EventHandlers(EventServices eventServices) {
    public Mono<ServerResponse> get(ServerRequest request) {
        return ServerResponse.ok().body(eventServices.all(), Event.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        var id = request.pathVariable("id");
        return ServerResponse.ok().body(eventServices.get(id), Event.class);
    }

    public Mono<ServerResponse> post(ServerRequest request) {
        return request
                .bodyToMono(Event.class)
                .log()
                .flatMap(eventServices::save)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> put(ServerRequest request) {
        var id = request.pathVariable("id");
        return request
                .bodyToMono(Event.class)
                .log()
                .flatMap(event -> eventServices.update(id, event))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        var id = request.pathVariable("id");
        return ServerResponse.ok().body(eventServices.delete(id), Void.class);
    }

    public Mono<ServerResponse> exampleAggregation(ServerRequest request) {
        return ServerResponse.noContent().build();
    }
    public Mono<ServerResponse> exampleQuery(ServerRequest request) {
        return ServerResponse.noContent().build();
    }
}
