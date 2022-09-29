package com.odeyalo.analog.netflix.searchmicroservice.repository;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VideoRepository extends ReactiveElasticsearchRepository<Video, String> {

    Flux<Video> findVideoByName(String name);

}
