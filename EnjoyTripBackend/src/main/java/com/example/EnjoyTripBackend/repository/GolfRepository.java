package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Golf;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GolfRepository {
    Long save(Golf golf);
}