package com.example.webfluxmongodb.repositories;

import com.example.webfluxmongodb.models.Event;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface EventRepository extends ReactiveMongoRepository<Event, String> {

    @Query(
            value = """
				{ 
					date:  {$date : ?0}
				}
			""",
			fields = "{_id: 0}"
	)
    Flux<Event> queryExample(LocalDate localDate);

	@Aggregation(
			pipeline = """
				{ 
					$match: {},
					$project: {_id: 0 }
				}
			"""
	)
	Flux<Event> aggregationExample();
}
