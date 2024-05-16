package com.example.EnjoyTripBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place extends BaseTimeDomain{
    private Long id;
    private String title;
    private String content;
    private String placeImageUrl;
    private String userEmail;
}