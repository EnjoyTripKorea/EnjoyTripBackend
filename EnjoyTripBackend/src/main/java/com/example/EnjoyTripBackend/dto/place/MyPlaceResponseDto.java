package com.example.EnjoyTripBackend.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlaceResponseDto {

    private String username;
    private String title;
    private String content;
    private String placeImageUrl;
    private String modifiedDate;

}