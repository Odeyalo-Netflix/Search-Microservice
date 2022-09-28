package com.odeyalo.analog.netflix.searchmicroservice.dto;

import com.odeyalo.analog.netflix.searchmicroservice.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchVideoResponseDTO {
    private String videoId;
    private String videoName;

    public Video toVideo() {
        return Video.builder().name(videoName).id(videoId).build();
    }

    public void fromVideo(Video video) {
        this.videoId = video.getId();
        this.videoName = video.getName();
    }
}
