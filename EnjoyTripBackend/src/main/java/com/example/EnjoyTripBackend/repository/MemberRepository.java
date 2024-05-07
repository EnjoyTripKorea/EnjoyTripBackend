package com.example.EnjoyTripBackend.repository;


import com.example.EnjoyTripBackend.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
    Long save(Member member);
}
