package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Place;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.ResponseResult;
import com.example.EnjoyTripBackend.dto.place.PlaceRequestDto;
import com.example.EnjoyTripBackend.dto.place.PlaceResponseDto;
import com.example.EnjoyTripBackend.dto.place.PlaceSearchwordRequestDto;
import com.example.EnjoyTripBackend.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public Long save(String userEmail, PlaceRequestDto placeRequestDto) {
        Place newPlace = Place.builder()
                .title(placeRequestDto.getTitle())
                .content(placeRequestDto.getContent())
                .placeImageUrl(placeRequestDto.getPlaceImageUrl())
                .userEmail(userEmail)
                .build();
        return placeRepository.save(newPlace);
    }

    public ResponseResult<List<PlaceResponseDto>> findAll(Pageable pageable) {
        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .build();
        return ResponseResult.of("여행 관광지 게시글 목록입니다.", placeRepository.findAll(requestList));
    }

    public ResponseResult<List<Optional<PlaceResponseDto>>> findSearchWordPlaces(PlaceSearchwordRequestDto placeSearchwordRequestDto, Pageable pageable){
        PageRequestList<?> requestList = PageRequestList.builder()
                .data(placeSearchwordRequestDto)
                .pageable(pageable)
                .build();
        List<Optional<PlaceResponseDto>> searchWordPlaces = placeRepository.findSearchWordPlaces(requestList);
//        List<PlaceResponseDto> filteredPlaces = searchWordPlaces.stream()
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
        return ResponseResult.of("검색어 관련 여행 관광지 게시글 목록입니다.", searchWordPlaces);
    }
}