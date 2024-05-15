package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Notice;
import com.example.EnjoyTripBackend.dto.Notice.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeRepository {
    Long save(Notice notice);
    Optional<NoticeDto> findById(Long id);
    List<NoticeDto> findAll();
    void update(@Param("id") Long id, @Param("noticeDto") NoticeDto noticeDto);
    void delete(Long id);
}