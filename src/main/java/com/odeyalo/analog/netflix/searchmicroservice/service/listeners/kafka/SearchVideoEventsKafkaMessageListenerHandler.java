package com.odeyalo.analog.netflix.searchmicroservice.service.listeners.kafka;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import com.odeyalo.analog.netflix.searchmicroservice.service.SearchVideoManager;
import com.odeyalo.support.clients.search.dto.SearchVideoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SearchVideoEventsKafkaMessageListenerHandler {
    private final Logger logger = LoggerFactory.getLogger(SearchVideoEventsKafkaMessageListenerHandler.class);
    private final SearchVideoManager manager;
    private final String SEARCH_REGISTRY_TOPIC = "SEARCH_REGISTRY_TOPIC";
    private final String DELETE_VIDEO_TOPIC = "DELETE_VIDEO_TOPIC";

    @Autowired
    public SearchVideoEventsKafkaMessageListenerHandler(SearchVideoManager manager) {
        this.manager = manager;
    }

    @KafkaListener(topics = SEARCH_REGISTRY_TOPIC, containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listen(SearchVideoEntity body) {
        Video video = Video.builder()
                .id(body.getId())
                .name(body.getName())
                .description(body.getDescription())
                .build();
        this.manager.saveVideo(video).doOnSuccess((s) -> this.logger.info("Saved: {}", video)).subscribe();
    }
}
