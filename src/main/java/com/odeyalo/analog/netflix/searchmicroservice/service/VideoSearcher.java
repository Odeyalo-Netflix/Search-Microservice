package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VideoSearcher {

    /**
     * Method to find all entities by given query without completions or suggestions
     * @param query - query that will be used to search
     * @return - list of found entities
     */
    Mono<List<Video>> findAllByQuery(String query);

    /**
     * Method to find all entities by given query with completions or suggestions
     * @param query - query that will be used to search
     * @return - list of found entities
     */
    Mono<List<Video>> findAllByQueryWithCompletion(String query);

}
