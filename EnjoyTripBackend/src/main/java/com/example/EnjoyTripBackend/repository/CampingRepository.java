package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Camping;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CampingRepository {
    Long save(Camping camping);
}