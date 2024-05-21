package com.example.EnjoyTripBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class House {
    private String addr1;
    private String addr2;
    private String areacode;
    private String contentid;
    private String firstimage;
    private String mapx;
    private String mapy;
    private String mlevel;
    private String tel;
    private String title;
    private String sigungucode;

}