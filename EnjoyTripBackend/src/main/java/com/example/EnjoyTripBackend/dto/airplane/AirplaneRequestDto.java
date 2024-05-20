package com.example.EnjoyTripBackend.dto.airplane;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirplaneRequestDto {
    private String depAirportNm;
    private String arrAirportNm;
    private String depPlandTime;
    private String airlineNm;
}
