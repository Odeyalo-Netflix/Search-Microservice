package com.odeyalo.analog.netflix.searchmicroservice.service.support;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * ReactiveHighLevelElasticsearchQuerySearcher default implementation
 */
public class DefaultReactiveHighLevelElasticsearchQuerySearcher implements ReactiveHighLevelElasticsearchQuerySearcher {
    private final ReactiveElasticsearchClient client;

    public DefaultReactiveHighLevelElasticsearchQuerySearcher(ReactiveElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public <T> Mono<List<T>> findAllByPlainQuery(String query, String index, String[] fields, ElasticsearchResponseConverter<SearchResponse, List<T>> converter) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(query));

        searchRequest.source(searchSourceBuilder);
        Mono<SearchResponse> response = client.searchForResponse(searchRequest);
        return response.map(converter::convertResponse);
    }

    @Override
    public <T> Mono<List<T>> findAllByCompletionQuery(String query, String index, String completionField, String[] fields, ElasticsearchResponseConverter<SearchResponse, List<T>> converter) {
        SearchRequest searchRequest = buildMultimatchQuery(query, index, completionField, fields, Fuzziness.ONE);
        Mono<SearchResponse> mono = client.searchForResponse(searchRequest);
        return mono.map(converter::convertResponse);
    }

    private SearchRequest buildMultimatchQuery(String query, String index, String completionField, String[] fields, Fuzziness fuzziness) {
        CompletionSuggestionBuilder builder = new CompletionSuggestionBuilder(completionField);
        builder.text(query);
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(query));
        searchSourceBuilder.suggest(new SuggestBuilder().addSuggestion("builder", builder));
        MultiMatchQueryBuilder matchBuilder = new MultiMatchQueryBuilder(query, fields);
        matchBuilder.fuzziness(fuzziness);
        searchSourceBuilder.query(matchBuilder);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }
}
