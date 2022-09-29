package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import com.odeyalo.analog.netflix.searchmicroservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DefaultElasticsearchSearchVideoEntityRegistrar implements SearchVideoEntityRegistrar {
    private final VideoRepository videoRepository;

    @Autowired
    public DefaultElasticsearchSearchVideoEntityRegistrar(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Mono<Video> save(Video video) {
        return this.videoRepository.save(video);
    }
}
