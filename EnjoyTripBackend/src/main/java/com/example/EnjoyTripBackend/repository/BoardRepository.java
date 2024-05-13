package com.example.EnjoyTripBackend.repository;


import com.example.EnjoyTripBackend.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface BoardRepository {
    Long save(Board board);
    Board findById(Long id)
    List<Board> findAll();
    Long update(BoardDto boardDto);
    void delete(Long id);
}

