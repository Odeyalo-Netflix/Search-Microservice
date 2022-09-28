package com.odeyalo.analog.netflix.searchmicroservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odeyalo.analog.netflix.searchmicroservice.service.support.DefaultReactiveHighLevelElasticsearchQuerySearcher;
import com.odeyalo.analog.netflix.searchmicroservice.service.support.ReactiveHighLevelElasticsearchQuerySearcher;
import com.odeyalo.analog.netflix.searchmicroservice.service.support.SearchResponseBody2VideoListElasticsearchResponseConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;

@Configuration
public class ElasticsearchClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ReactiveHighLevelElasticsearchQuerySearcher querySearcher(ReactiveElasticsearchClient client) {
        return new DefaultReactiveHighLevelElasticsearchQuerySearcher(client);
    }

    @Bean
    public SearchResponseBody2VideoListElasticsearchResponseConverter elasticsearchResponseConverter(ObjectMapper mapper) {
        return new SearchResponseBody2VideoListElasticsearchResponseConverter(mapper);
    }
}
