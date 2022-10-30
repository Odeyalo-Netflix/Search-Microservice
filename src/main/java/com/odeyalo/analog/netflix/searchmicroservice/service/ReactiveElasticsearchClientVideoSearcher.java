package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import com.odeyalo.analog.netflix.searchmicroservice.service.support.ReactiveHighLevelElasticsearchQuerySearcher;
import com.odeyalo.analog.netflix.searchmicroservice.service.support.SearchResponseBody2VideoListElasticsearchResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class ReactiveElasticsearchClientVideoSearcher implements VideoSearcher {
    private final ReactiveHighLevelElasticsearchQuerySearcher searcher;
    private final SearchResponseBody2VideoListElasticsearchResponseConverter converter;

    @Autowired
    public ReactiveElasticsearchClientVideoSearcher(ReactiveHighLevelElasticsearchQuerySearcher searcher, SearchResponseBody2VideoListElasticsearchResponseConverter converter) {
        this.searcher = searcher;
        this.converter = converter;
    }

    @Override
    public Mono<List<Video>> findAllByQuery(String query) {
        return this.searcher.findAllByPlainQuery(query, "video", new String[]{"name", "description"}, converter);
    }

    @Override
    public Mono<List<Video>> findAllByQueryWithCompletion(String query) {
        return this.searcher.findAllByCompletionQuery(query, "video", "name_suggester", new String[]{"name", "description"}, converter);
    }
}
