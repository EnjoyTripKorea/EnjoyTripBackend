package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Place;
import com.example.EnjoyTripBackend.dto.place.PlaceResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceRepository {

    Long save(Place place);
    List<PlaceResponseDto> findAll();
}