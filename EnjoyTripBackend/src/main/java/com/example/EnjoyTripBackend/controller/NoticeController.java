package com.example.EnjoyTripBackend.controller;

import com.example.EnjoyTripBackend.dto.Notice.NoticeDto;
import com.example.EnjoyTripBackend.dto.Notice.NoticeListResponseDto;
import com.example.EnjoyTripBackend.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService NoticeService;

    @PostMapping("/")
    public ResponseEntity<String> save(@Valid @RequestBody NoticeDto noticeDto){
        Long id = NoticeService.save(noticeDto);
        return ResponseEntity.status(CREATED).body("공지사항 등록 완료");
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDto> findById(@PathVariable("id") Long id) {
        NoticeDto noticeDto = NoticeService.findById(id);
        return ResponseEntity.status(OK).body(noticeDto);
    }

//    @GetMapping("/")
//    public ResponseEntity<List<NoticeDto>> findAll() {
//        List<NoticeDto> noticeList = NoticeService.findAll();
//        return ResponseEntity.status(OK).body(noticeList);
//    }

    @GetMapping("/")
    public ResponseEntity<NoticeListResponseDto> findAll() {
        NoticeListResponseDto noticeList = NoticeService.findAll();
        
        return ResponseEntity.status(OK).body(noticeList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @Valid @RequestBody NoticeDto noticeDto) {
        NoticeService.update(id, noticeDto);
        return ResponseEntity.status(OK).body("공지사항 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        NoticeService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}