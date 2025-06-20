package com.example.demo.meeting;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface MeetingRepository extends ReactiveMongoRepository<Meeting, String> {
    Flux<Meeting> findAllBy(Pageable pageable);
}
