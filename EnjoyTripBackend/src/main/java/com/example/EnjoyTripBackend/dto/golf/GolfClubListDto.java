package com.example.EnjoyTripBackend.dto.golf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GolfClubListDto {

    private String golfclub_name;
    private String area_name1;
    private String area_name2;
    private double avg_grade;
    private int green_fee_week;
    private int green_fee_weekend;
    private String teetime_date;
    private double latitude;
    private double longitude;
    private String thmbn_file_url;

}