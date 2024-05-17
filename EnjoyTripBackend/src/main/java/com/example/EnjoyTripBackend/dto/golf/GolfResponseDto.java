package com.example.EnjoyTripBackend.dto.golf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GolfResponseDto {

    private Long golf_id;
    private String golfclub_name;
    private String location;
    private String date;
    private String golf_imageUrl;
    private String green_fee_week;
    private String green_fee_weekend;
    private double latitude;
    private double longitude;
}