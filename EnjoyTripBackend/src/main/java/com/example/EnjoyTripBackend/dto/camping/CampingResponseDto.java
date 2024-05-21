package com.example.EnjoyTripBackend.dto.camping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampingResponseDto {

    private String name;
    private String Intro;
    private String zipcode;
    private String address;
    private double latitude;
    private double longitude;
    private String imageUrl;
    private String homepage;

}