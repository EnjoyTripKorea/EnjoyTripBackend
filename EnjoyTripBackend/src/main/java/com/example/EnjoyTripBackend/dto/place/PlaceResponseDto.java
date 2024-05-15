package com.example.EnjoyTripBackend.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {
    private Long placeId;
    private String title;
    private String content;
    private String placeImageUrl;
    private LocalDate createdDate;
}