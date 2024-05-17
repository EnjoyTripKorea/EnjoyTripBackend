package com.example.EnjoyTripBackend.dto.golf;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GolfRequestDto {
    private String date;
    private String location;
}