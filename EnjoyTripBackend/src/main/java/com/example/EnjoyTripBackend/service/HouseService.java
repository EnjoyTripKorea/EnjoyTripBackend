package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.House;
import com.example.EnjoyTripBackend.dto.NonPagingResponseResult;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.ResponseResult;
import com.example.EnjoyTripBackend.dto.house.HouseRequestDto;
import com.example.EnjoyTripBackend.dto.house.HouseResponseDto;
import com.example.EnjoyTripBackend.dto.house.HouseItem;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HouseService {

    private final HouseRepository houseRepository;

    @Transactional
    public void save(HouseItem item) {
        House house = House.builder()
                .addr1(item.getAddr1())
                .addr2(item.getAddr2())
                .areacode(item.getAreacode())
                .contentid(item.getContentid())
                .firstimage(item.getFirstimage())
                .mapx(item.getMapx())
                .mapy(item.getMapy())
                .mlevel(item.getMlevel())
                .tel(item.getTel())
                .title(item.getTitle())
                .sigungucode(item.getSigungucode())
                .build();

        houseRepository.save(house);
    }

    public ResponseResult<List<HouseResponseDto>> houseList(Pageable pageable) {
        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .build();

        long totalCount = houseRepository.findTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());

        return ResponseResult.of("숙박시설 정보 게시글 목록입니다.", houseRepository.findAll(requestList),totalPages);
    }

    public NonPagingResponseResult<HouseResponseDto> findById(String id) {
        return NonPagingResponseResult.of("숙박시설 상세 정보 게시글 입니다.", houseRepository.findById(id).orElseThrow(() -> new EnjoyTripException(ErrorCode.CONTENT_NOT_FOUNT)));
    }

    public ResponseResult<List<HouseResponseDto>> houseSearchList(Pageable pageable, HouseRequestDto houseRequestDto) {
        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .data(houseRequestDto)
                .build();
        List<HouseResponseDto> houses = houseRepository.findAllBySearch(requestList);
        long totalCount = houses.size();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());
        return ResponseResult.of("검색어 기반 숙박시설 정보 게시글 목록입니다.", houses, totalPages);
    }
}