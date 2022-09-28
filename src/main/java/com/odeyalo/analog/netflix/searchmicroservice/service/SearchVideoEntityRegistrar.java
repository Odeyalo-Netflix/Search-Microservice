package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;

public interface SearchVideoEntityRegistrar {
    /**
     * Saves the given video to storage
     * @param video - video to save
     */
    void save(Video video);

}
