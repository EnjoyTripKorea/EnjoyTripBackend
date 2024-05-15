package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Place;
import com.example.EnjoyTripBackend.dto.place.PlaceRequestDto;
import com.example.EnjoyTripBackend.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public Long save(String userEmail, PlaceRequestDto placeRequestDto) {
        Place newPlace = Place.builder()
                .title(placeRequestDto.getTitle())
                .content(placeRequestDto.getContent())
                .placeImageUrl(placeRequestDto.getPlaceImageUrl())
                .userEmail(userEmail)
                .build();
        return placeRepository.save(newPlace);
    }
}