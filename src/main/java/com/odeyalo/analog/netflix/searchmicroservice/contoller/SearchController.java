package com.odeyalo.analog.netflix.searchmicroservice.contoller;

import com.odeyalo.analog.netflix.searchmicroservice.dto.SearchVideoResponseDTO;
import com.odeyalo.analog.netflix.searchmicroservice.dto.VideoSuggestResponseDTO;
import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import com.odeyalo.analog.netflix.searchmicroservice.repository.VideoRepository;
import com.odeyalo.analog.netflix.searchmicroservice.service.VideoSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
@CrossOrigin("*")
public class SearchController {
    private final VideoRepository repository;
    private final VideoSearcher searcher;
    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @GetMapping("/video/all")
    public Flux<Video> list() {
        return this.repository.findAll();
    }

    public SearchController(VideoRepository repository, VideoSearcher searcher) {
        this.repository = repository;
        this.searcher = searcher;
    }

    @GetMapping("/suggest")
    public Mono<List<VideoSuggestResponseDTO>> suggest(@RequestParam(name = "q") String query) {
        return this.searcher.findAllByQuery(query).map(x -> x.stream().map(video -> new VideoSuggestResponseDTO(video.getName())).collect(Collectors.toList()));
    }

    @PostMapping("/save")
    public Mono<?> save(@RequestBody Video video) {
        return this.repository.save(video);
    }

    @PostMapping("/saveAll")
    public Flux<?> saveAll(@RequestBody List<Video> videos) {
        return this.repository.saveAll(videos);
    }

    @GetMapping("/video")
    public Mono<List<SearchVideoResponseDTO>> searchByQuery(@RequestParam(name = "q") String query) {
        return this.searcher.findAllByQuery(query).map(mono -> {
            return mono.stream().map(video -> {
                SearchVideoResponseDTO response = new SearchVideoResponseDTO();
                response.fromVideo(video);
                return response;
            }).collect(Collectors.toList());
        });
    }
}
