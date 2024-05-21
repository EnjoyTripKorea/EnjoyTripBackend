package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Camping;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.camping.CampingResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CampingRepository {
    Long save(Camping camping);
    Long findTotalCount();
    List<CampingResponseDto> findAll(PageRequestList<?> requestList);
}