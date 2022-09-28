package com.odeyalo.analog.netflix.searchmicroservice.service.support;

@FunctionalInterface
public interface ElasticsearchResponseConverter<T, R> {
    /**
     * Convert a response to DTO object
     * @param body - body that will be converted
     * @return - converted body
     */
    R convertResponse(T body);
}
