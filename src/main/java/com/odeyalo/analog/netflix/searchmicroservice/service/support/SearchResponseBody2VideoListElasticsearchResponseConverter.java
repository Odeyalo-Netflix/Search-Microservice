package com.odeyalo.analog.netflix.searchmicroservice.service.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert SearchResponse body to Video and collect it to the List
 */
public class SearchResponseBody2VideoListElasticsearchResponseConverter implements ElasticsearchResponseConverter<SearchResponse, List<Video>> {
    private final ObjectMapper mapper;

    public SearchResponseBody2VideoListElasticsearchResponseConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Video> convertResponse(SearchResponse response) {
        SearchHits hits = response.getHits();
        try {
            List<Video> videos = new ArrayList<>();
            for (SearchHit hit : hits) {
                String body = hit.getSourceAsString();
                Video video = mapper.readValue(body, Video.class);
                video.setId(hit.getId());
                videos.add(video);
            }
            return videos;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert json body", e);
        }
    }
}
