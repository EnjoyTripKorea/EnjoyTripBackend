package com.example.EnjoyTripBackend.controller;

import com.example.EnjoyTripBackend.dto.NonPagingResponseResult;
import com.example.EnjoyTripBackend.dto.ResponseResult;
import com.example.EnjoyTripBackend.dto.golf.GolfRequestDto;
import com.example.EnjoyTripBackend.dto.golf.GolfResponseDto;
import com.example.EnjoyTripBackend.service.GolfService;
import com.example.EnjoyTripBackend.util.LimitedSizePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GolfController {

    private final GolfService golfService;

    @GetMapping("/golf")
    @LimitedSizePagination(maxSize = 20)
    public ResponseEntity<ResponseResult<List<GolfResponseDto>>> golfList(@PageableDefault(size = 20) Pageable pageable){
        return ResponseEntity.ok().body(golfService.golfList(pageable));
    }

    @GetMapping("/golf/{id}")
    public ResponseEntity<NonPagingResponseResult<GolfResponseDto>> golfDetail(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(golfService.findById(id));
    }

    @GetMapping("/golf/search")
    @LimitedSizePagination(maxSize = 20)
    public ResponseEntity<ResponseResult<List<GolfResponseDto>>> golfSearchList(@PageableDefault(size = 20) Pageable pageable, @RequestBody GolfRequestDto golfRequestDto){
        return ResponseEntity.ok().body(golfService.golfSearchList(pageable, golfRequestDto));
    }

}