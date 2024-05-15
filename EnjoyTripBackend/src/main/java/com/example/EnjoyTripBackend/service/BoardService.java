package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Board;
import com.example.EnjoyTripBackend.dto.Board.BoardDto;
import com.example.EnjoyTripBackend.dto.Board.BoardListResponseDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public BoardListResponseDto findAll(){
        BoardListResponseDto responseDto = new BoardListResponseDto();
        responseDto.setData(boardRepository.findAll());
        if (responseDto.getData().isEmpty()) {
            responseDto.setMsg("게시글이 없습니다.");
        } else {
            responseDto.setMsg("게시글 목록입니다.");
        }
        return responseDto;
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