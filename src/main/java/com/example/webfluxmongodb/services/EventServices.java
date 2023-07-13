package com.example.webfluxmongodb.services;

import com.example.webfluxmongodb.models.Event;
import com.example.webfluxmongodb.repositories.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public record EventServices(EventRepository eventRepository) {
    public Mono<Event> get(String id) {
        return eventRepository.findById(id);
    }

    public Flux<Event> all() {
        return eventRepository.findAll();
    }

    public Mono<Event> save(Event event) {
        return eventRepository.save(event);
    }

    public Mono<Event> update(String id, Event event) {
        return Mono
                .zip(
                        get(id),
                        Mono.just(event),
                        (db, r) -> {
                            return new Event(id, nonNull(r.date()) ? r.date() : db.date(), nonNull(r.message()) ? r.message() : db.message());
                        })
                .flatMap(event1 -> eventRepository.save(event1));
    }

    public Mono<Void> delete(String id) {
        return eventRepository.deleteById(id);
    }
}
