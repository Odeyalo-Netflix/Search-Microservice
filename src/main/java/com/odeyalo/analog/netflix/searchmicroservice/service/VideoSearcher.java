package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VideoSearcher {

    /**
     * Method to find all entities by given query
     * @param query - query that will be used to search
     * @return - list of found entities
     */
    Mono<List<Video>> findAllByQuery(String query);

}
