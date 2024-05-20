package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Airplane;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.airplane.AirplaneResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirplaneRepository {
    Long save(Airplane airplane);
    List<AirplaneResponseDto> findAll(PageRequestList<?> requestList);
    Optional<AirplaneResponseDto> findById(Long id);
    List<AirplaneResponseDto> findAllBySearch(PageRequestList<?> requestList);
    Long findTotalCount();
}