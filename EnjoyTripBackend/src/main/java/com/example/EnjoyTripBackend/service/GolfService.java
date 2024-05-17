package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Golf;
import com.example.EnjoyTripBackend.dto.golf.GolfClubListDto;
import com.example.EnjoyTripBackend.repository.GolfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GolfService {

    private final GolfRepository golfRepository;

    @Transactional
    public Long save(String today, GolfClubListDto golfclubListDto) {
        Golf golf = Golf.builder()
                .place(golfclubListDto.getGolfclub_name().replace("&#40;P&#41;", "").replace("&#40;","").replace("&#41;",""))
                .location(golfclubListDto.getArea_name1() + golfclubListDto.getArea_name2())
                .date(today)
                .golfImageUrl(golfclubListDto.getThmbn_file_url())
                .greenFeeWeek(golfclubListDto.getGreen_fee_week())
                .greenFeeWeekend(golfclubListDto.getGreen_fee_weekend())
                .latitude(golfclubListDto.getLatitude())
                .longitude(golfclubListDto.getLongitude())
                .build();

        return golfRepository.save(golf);
    }
}