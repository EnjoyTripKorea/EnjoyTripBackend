package com.example.EnjoyTripBackend.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {
    private Long placeId;
    private String title;
    private String content;
    private String placeImageUrl;
    private String userEmail;
    private String createdDate;
}