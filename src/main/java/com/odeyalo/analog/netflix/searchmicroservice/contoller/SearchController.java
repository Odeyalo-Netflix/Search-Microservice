package com.odeyalo.analog.netflix.searchmicroservice.contoller;

import com.odeyalo.analog.netflix.searchmicroservice.dto.SearchVideoResponseDTO;
import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import com.odeyalo.analog.netflix.searchmicroservice.repository.VideoRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/search")
@CrossOrigin("*")
public class SearchController {
    private final VideoRepository repository;

    public SearchController(VideoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/video/all")
    public Flux<Video> list() {
        return this.repository.findAll();
    }

    @PostMapping("/save")
    public Mono<?> save(@RequestBody Video video) {

        return this.repository.save(video);
    }

    @GetMapping("/video")
    public Flux<Video> searchByQuery(@RequestParam(name = "q") String query) {
        return this.repository.findVideoByName(query);
    }
}
