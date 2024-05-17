package com.example.EnjoyTripBackend.dto.golf;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GolfDataDto {
    private int totalCnt;
    private List<GolfClubListDto> golfclubList;
}