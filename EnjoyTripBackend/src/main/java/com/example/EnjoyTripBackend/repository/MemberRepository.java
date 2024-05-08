package com.example.EnjoyTripBackend.repository;


import com.example.EnjoyTripBackend.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberRepository {
    Long save(Member member);

    Optional<Member> findByEmail(String email);
}
