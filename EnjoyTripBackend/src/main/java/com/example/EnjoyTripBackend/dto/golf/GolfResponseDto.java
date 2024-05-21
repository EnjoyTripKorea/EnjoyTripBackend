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

    private Long golfId;
    private String golfclubName;
    private String location;
    private String date;
    private String golfImageUrl;
    private String greenFeeWeek;
    private String greenFeeWeekend;
    private double latitude;
    private double longitude;
}