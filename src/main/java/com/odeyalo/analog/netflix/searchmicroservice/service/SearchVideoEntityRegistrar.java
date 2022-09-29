package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import reactor.core.publisher.Mono;

public interface SearchVideoEntityRegistrar {
    /**
     * Save given video to storage
     * @param video - video to save
     * @return - mono with saved video
     */
    Mono<Video> save(Video video);

}
