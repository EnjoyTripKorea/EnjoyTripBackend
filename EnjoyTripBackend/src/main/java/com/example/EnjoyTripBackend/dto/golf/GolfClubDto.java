package com.example.EnjoyTripBackend.dto.golf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GolfClubDto {

    private int result;
    private String message;
    private GolfDataDto data;

}