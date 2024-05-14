package com.example.EnjoyTripBackend.controller;

import com.example.EnjoyTripBackend.dto.Board.BoardDto;
import com.example.EnjoyTripBackend.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/")
    public ResponseEntity<String> save(@Valid @RequestBody BoardDto boardDto){
        Long id = boardService.save(boardDto);
        return ResponseEntity.status(CREATED).body("게시글 등록 완료");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> findById(@PathVariable("id") Long id) {
        BoardDto boardDto = boardService.findById(id);
        return ResponseEntity.status(OK).body(boardDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<BoardDto>> findAll() {
        List<BoardDto> boardList = boardService.findAll();
        return ResponseEntity.status(OK).body(boardList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @Valid @RequestBody BoardDto boardDto) {

        boardService.update(id, boardDto);
        return ResponseEntity.status(OK).body("게시글 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}