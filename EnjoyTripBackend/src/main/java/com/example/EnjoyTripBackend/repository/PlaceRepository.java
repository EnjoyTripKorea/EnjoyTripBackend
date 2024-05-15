package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Place;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlaceRepository {

    Long save(Place place);
}