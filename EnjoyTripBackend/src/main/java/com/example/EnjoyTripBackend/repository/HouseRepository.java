package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.House;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.house.HouseResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HouseRepository {
    Long save(House house);
    List<HouseResponseDto> findAll(PageRequestList<?> requestList);
    Optional<HouseResponseDto> findById(String id);
    List<HouseResponseDto> findAllBySearch(PageRequestList<?> requestList);
    Long findTotalCount();
}