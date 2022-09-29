package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Manager to wrap up default logic to working with Video entity
 */
public interface SearchVideoManager {
    /**
     * Save the given video
     * @param video - video to save
     */
    Mono<Video> saveVideo(Video video);

    /**
     * Delete video
     * @param video - video to delete
     * @return - result of deletion
     */
    Mono<Boolean> deleteVideo(Video video);

    /**
     * Search by query and return result
     * @param query - query using to search
     * @param completion - true if completion suggestions needed, otherwise false
     * @return - flux with found videos
     */
    Flux<Video> getVideo(String query, boolean completion);

    Mono<Boolean> contains(Video video);
}
