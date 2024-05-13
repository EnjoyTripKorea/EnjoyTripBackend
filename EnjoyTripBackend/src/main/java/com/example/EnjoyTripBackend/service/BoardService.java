package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Board;
import com.example.EnjoyTripBackend.dto.Board.BoardDto;
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

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new EnjoyTripException(ErrorCode.BOARD_NOT_FOUND));
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
//        return boardRepository.findAll().stream().map(this::Board).collect(Collector.toList());
    }

    @Transactional
    public Long update(BoardDto boardDto){
        Optional<Board> optionalBoard = boardRepository.findById(boardDto.getId());
        if (!optionalBoard.isPresent()) {
            throw new EnjoyTripException(
                    "Board is not present in the database");
        }
        return boardRepository.update(boardDto);
    }

    public void delete(Long id){
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (!optionalBoard.isPresent()) {
            throw new EnjoyTripException(
                    "Board is not present in the database");
        }
        boardRepository.delete(id);
    }
}
