package com.odeyalo.analog.netflix.searchmicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MultipleVideoSuggestResponseDTO {
    private List<VideoSuggestResponseDTO> suggestions;
}
