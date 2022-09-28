package com.odeyalo.analog.netflix.searchmicroservice.service.support;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Provide high level working with Elasticsearch search API
 */
public interface ReactiveHighLevelElasticsearchQuerySearcher {

    /**
     * Method to find all entities by given query.
     * THIS METHOD DOES NOT USE COMPLETION SUGGESTER.
     * @param query - query that will be used to search
     * @param index - index where search will be
     * @param fields - fields when search will be
     * @param converter - converter to convert SearchResponse to Java object
     * @param <T> - type of class to cast
     * @return - list of all found entities
     */
    <T> Mono<List<T>> findAllByPlainQuery(String query, String index, String[] fields, ElasticsearchResponseConverter<SearchResponse, List<T>> converter);

    /**
     * Method to find all entities by given query
     * @param query - query to search
     * @param index - index to search for
     * @param completionField - field to build Completion suggester
     * @param fields - fields to search for
     * @param converter - converter to convert SearchResponse to Java objects
     * @param <T> - type of class to cast
     * @return - list of all found entities
     */
    <T> Mono<List<T>> findAllByCompletionQuery(String query, String index, String completionField, String[] fields, ElasticsearchResponseConverter<SearchResponse, List<T>> converter);


    /**
     * Support class to wrap given object to Mono
     * @param object - object to convert
     * @param <T> - type of object to return
     * @return - wrapped object to Mono class
     */
    default <T> Mono<?> toMono(T object) {
        return Mono.just(object);
    }

    /**
     * Support class to wrap given class to Flux
     * @param object - object to convert
     * @param <T> - type of object to return
     * @return - wrapped object to Flux class
     */

    default <T> Flux<?> toFlux(T object) {
        return Flux.just(object);
    }
}
