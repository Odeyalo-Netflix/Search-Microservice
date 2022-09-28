package com.odeyalo.analog.netflix.searchmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@SpringBootApplication
@EnableReactiveElasticsearchRepositories
public class SearchMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchMicroserviceApplication.class, args);
    }
}
