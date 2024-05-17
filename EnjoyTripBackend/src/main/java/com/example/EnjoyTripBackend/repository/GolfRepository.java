package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Golf;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.golf.GolfResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GolfRepository {
    Long save(Golf golf);
    List<GolfResponseDto> findAll(PageRequestList<?> requestList);
    Optional<GolfResponseDto> findById(Long id);
    List<GolfResponseDto> findAllBySearch(PageRequestList<?> requestList);
    Long findTotalCount();
}