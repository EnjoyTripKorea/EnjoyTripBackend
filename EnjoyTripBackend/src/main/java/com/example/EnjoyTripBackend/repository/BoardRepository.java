package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Board;
import com.example.EnjoyTripBackend.dto.Board.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardRepository {
    Long save(Board board);
    Optional<BoardDto> findById(Long id);
    List<BoardDto> findAll();
    void update(@Param("id") Long id, @Param("boardDto") BoardDto boardDto);
    void delete(Long id);
}