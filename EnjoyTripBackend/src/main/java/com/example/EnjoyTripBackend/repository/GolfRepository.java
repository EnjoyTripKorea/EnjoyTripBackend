package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Golf;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.golf.GolfResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GolfRepository {
    Long save(Golf golf);
    List<GolfResponseDto> findAll(PageRequestList<?> requestList);
}