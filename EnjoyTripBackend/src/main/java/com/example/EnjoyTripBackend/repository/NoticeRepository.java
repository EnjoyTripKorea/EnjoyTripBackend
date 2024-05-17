package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Notice;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.notice.NoticeDto;
import com.example.EnjoyTripBackend.dto.notice.NoticeResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeRepository {
    Long save(Notice notice);
    Optional<NoticeDto> findById(Long id);
    List<NoticeResponseDto> findAll(PageRequestList<?> requestList);
    Long findTotalCount();
    void update(@Param("id") Long id, @Param("noticeDto") NoticeDto noticeDto);
    void delete(Long id);
}