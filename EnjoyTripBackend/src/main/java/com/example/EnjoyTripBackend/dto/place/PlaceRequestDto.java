package com.example.EnjoyTripBackend.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceRequestDto {
    private String title;
    private String content;
    private String placeImageUrl;
    private String userEmail;
}