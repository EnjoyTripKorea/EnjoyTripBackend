package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Board;
import com.example.EnjoyTripBackend.dto.Board.BoardDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardDto boardDto) {
        Board board = Board.builder()
                .memberId(boardDto.getMemberId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();
        return boardRepository.save(board);
    }

    public BoardDto findById(Long id) {
        return boardRepository.findById(id).orElseThrow(()-> new EnjoyTripException(ErrorCode.BOARD_NOT_FOUND));
    }

    public List<BoardDto> findAll(){
        return boardRepository.findAll();
    }

    @Transactional
    public void update(Long id, BoardDto boardDto) {
        boardRepository.update(id, boardDto);
    }

    @Transactional
    public void delete(Long id){
        boardRepository.delete(id);
    }
}