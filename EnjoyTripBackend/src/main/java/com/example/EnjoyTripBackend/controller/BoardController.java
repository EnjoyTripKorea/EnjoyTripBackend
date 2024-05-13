package com.example.EnjoyTripBackend.controller;

import com.example.EnjoyTripBackend.dto.Board.BoardDto;
import com.example.EnjoyTripBackend.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

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
    public ResponseEntity<board> findById(@PathVariable("id") Long id) {
        Board board = boardService.findById(id);
        return ResponseEntity.status(OK).body(board);
    }

    @GetMapping
    public ResponseEntity<List<board>> findAll() {
        List<board> boardList = boardService.findAll();
        return ResponseEntity.status(OK).body(boardList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@Valid @RequestBody BoardDto boardDto) {
        Long boardId = boardService.update(boardDto);
        return ResponseEntity.status(OK).body(boardId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}