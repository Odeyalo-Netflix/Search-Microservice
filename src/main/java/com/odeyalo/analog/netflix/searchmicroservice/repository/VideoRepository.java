package com.odeyalo.analog.netflix.searchmicroservice.repository;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VideoRepository extends ReactiveCrudRepository<Video, Long> {

    Flux<Video> findVideoByName(String name);

}
