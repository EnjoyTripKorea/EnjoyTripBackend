package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Place;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.place.PlaceResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlaceRepository {

    Long save(Place place);
    List<PlaceResponseDto> findAll(PageRequestList<?> requestList);
    List<Optional<PlaceResponseDto>> findSearchWordPlaces(PageRequestList<?> requestList);
    Optional<PlaceResponseDto> findOne(Long id);
}