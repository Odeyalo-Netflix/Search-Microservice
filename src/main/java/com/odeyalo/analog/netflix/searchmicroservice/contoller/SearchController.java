package com.odeyalo.analog.netflix.searchmicroservice.contoller;

import com.odeyalo.analog.netflix.searchmicroservice.dto.SearchVideoResponseDTO;
import com.odeyalo.analog.netflix.searchmicroservice.dto.VideoSuggestResponseDTO;
import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import com.odeyalo.analog.netflix.searchmicroservice.repository.VideoRepository;
import com.odeyalo.analog.netflix.searchmicroservice.service.SearchVideoManager;
import com.odeyalo.analog.netflix.searchmicroservice.service.VideoSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final SearchVideoManager manager;
    private final VideoSearcher searcher;
    private final Logger logger = LoggerFactory.getLogger(SearchController.class);


    @Autowired
    public SearchController(VideoRepository repository, SearchVideoManager manager, VideoSearcher searcher) {
        this.repository = repository;
        this.manager = manager;
        this.searcher = searcher;
    }

    @GetMapping("/video/all")
    public Flux<Video> list() {
        return this.repository.findAll();
    }


    @GetMapping("/suggest")
    public Mono<List<VideoSuggestResponseDTO>> suggest(@RequestParam(name = "q") String query, @RequestParam(defaultValue = "default") boolean autoComplete) {
        if (autoComplete) {
            return this.searcher.findAllByQueryWithCompletion(query).map(x -> x.stream().map(video -> new VideoSuggestResponseDTO(video.getName())).collect(Collectors.toList()));

        }
        return this.searcher.findAllByQuery(query).map(x -> x.stream().map(video -> new VideoSuggestResponseDTO(video.getName())).collect(Collectors.toList()));
    }

    @GetMapping("/video")
    public Flux<SearchVideoResponseDTO> searchByQuery(@RequestParam(name = "q") String query) {
        return this.manager.getVideo(query, true).map(video -> {
            SearchVideoResponseDTO response = new SearchVideoResponseDTO();
            response.fromVideo(video);
            return response;
        });
    }

    @PostMapping("/save")
    public Mono<?> save(@RequestBody Video video) {
        return this.manager.saveVideo(video);
    }

    @PostMapping("/saveAll")
    public Flux<?> saveAll(@RequestBody List<Video> videos) {
        return this.repository.saveAll(videos);
    }
}
