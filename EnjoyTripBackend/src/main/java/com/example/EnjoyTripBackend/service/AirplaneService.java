package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Airplane;
import com.example.EnjoyTripBackend.dto.NonPagingResponseResult;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.ResponseResult;
import com.example.EnjoyTripBackend.dto.airplane.AirplaneRequestDto;
import com.example.EnjoyTripBackend.dto.airplane.AirplaneResponseDto;
import com.example.EnjoyTripBackend.dto.golf.GolfRequestDto;
import com.example.EnjoyTripBackend.dto.golf.GolfResponseDto;
import com.example.EnjoyTripBackend.dto.place.PlaceResponseDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    @Transactional
    public void save(AirplaneResponseDto airplaneResponseDto) {
        Airplane airplane = Airplane.builder()
                .airlineNm(airplaneResponseDto.getAirlineNm())
                .arrAirportNm(airplaneResponseDto.getArrAirportNm())
                .depAirportNm(airplaneResponseDto.getDepAirportNm())
                .arrPlandTime(airplaneResponseDto.getArrPlandTime())
                .depPlandTime(airplaneResponseDto.getDepPlandTime())
                .economyCharge(airplaneResponseDto.getEconomyCharge())
                .prestigeCharge(airplaneResponseDto.getPrestigeCharge())
                .vihicleId(airplaneResponseDto.getVihicleId())
                .build();

        airplaneRepository.save(airplane);
    }

    public ResponseResult<List<AirplaneResponseDto>> airplaneList(Pageable pageable) {
        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .build();

        long totalCount = airplaneRepository.findTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());

        return ResponseResult.of("항공권 정보 게시글 목록입니다.", airplaneRepository.findAll(requestList),totalPages);
    }

    public NonPagingResponseResult<AirplaneResponseDto> findById(Long id) {
        return NonPagingResponseResult.of("항공권 상세 정보 게시글 입니다.", airplaneRepository.findById(id).orElseThrow(() -> new EnjoyTripException(ErrorCode.CONTENT_NOT_FOUNT)));
    }

    public ResponseResult<List<AirplaneResponseDto>> airplaneSearchList(Pageable pageable, AirplaneRequestDto airplaneRequestDto) {
        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .data(airplaneRequestDto)
                .build();
        List<AirplaneResponseDto> airplanes = airplaneRepository.findAllBySearch(requestList);
        long totalCount = airplanes.size();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());
        return ResponseResult.of("검색어 기반 항공권 정보 게시글 목록입니다.", airplanes, totalPages);
    }
}
