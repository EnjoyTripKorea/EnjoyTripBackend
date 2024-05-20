package com.example.EnjoyTripBackend.dto.airplane;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneResponseDto {
    private Long id;
    private String airlineNm;
    private String arrAirportNm;
    private String depAirportNm;
    private String arrPlandTime;
    private String depPlandTime;
    private String economyCharge;
    private String prestigeCharge;
    private String vihicleId;
}
