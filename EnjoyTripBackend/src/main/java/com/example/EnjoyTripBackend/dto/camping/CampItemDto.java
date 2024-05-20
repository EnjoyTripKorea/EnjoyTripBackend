package com.example.EnjoyTripBackend.dto.camping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampItemDto {
    private String facltNm;
    private String intro;
    private String tel;
    private String zipcode;
    private double mapY;
    private double mapX;
    private String lineIntro;
    private String firstImageUrl;
    private String addr1;
    private String homepage;
}