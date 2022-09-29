package com.odeyalo.analog.netflix.searchmicroservice.service;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import com.odeyalo.analog.netflix.searchmicroservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ElasticsearchSearchVideoManager implements SearchVideoManager {
    private final SearchVideoEntityRegistrar registrar;
    private final VideoSearcher videoSearcher;
    private final VideoRepository videoRepository;

    @Autowired
    public ElasticsearchSearchVideoManager(SearchVideoEntityRegistrar registrar, VideoSearcher videoSearcher, VideoRepository videoRepository) {
        this.registrar = registrar;
        this.videoSearcher = videoSearcher;
        this.videoRepository = videoRepository;
    }

    @Override
    public Mono<Video> saveVideo(Video video) {
        return this.registrar.save(video);
    }

    @Override
    public Mono<Boolean> deleteVideo(Video video) {
        this.videoRepository.delete(video);
        return Mono.just(true);
    }

    @Override
    public Flux<Video> getVideo(String query, boolean completion) {
        if (completion) {
            Mono<List<Video>> mono = this.videoSearcher.findAllByQueryWithCompletion(query);
            return toFlux(mono).log();

        }
        Mono<List<Video>> mono = this.videoSearcher.findAllByQuery(query);
        return toFlux(mono).log();
    }

    @Override
    public Mono<Boolean> contains(Video video) {
        String name = video.getName();
        Mono<Video> mono = this.videoRepository.findById(name);
        return mono.hasElement();
    }

    protected <T> Flux<T> toFlux(Mono<List<T>> mono) {
        return mono.flatMapMany(Flux::fromIterable);
    }
}
