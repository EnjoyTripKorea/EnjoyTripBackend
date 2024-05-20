package com.example.EnjoyTripBackend.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Airplane {
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
