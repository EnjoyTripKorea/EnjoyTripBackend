package com.example.EnjoyTripBackend.dto.house;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponseDto {
    private Long id;
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