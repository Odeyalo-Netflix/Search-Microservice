package com.odeyalo.analog.netflix.searchmicroservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video  {
    @Id
    private Long id;
    private String name;
    private String description;
}
