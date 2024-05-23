package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Place;
import com.example.EnjoyTripBackend.dto.NonPagingResponseResult;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.ResponseResult;
import com.example.EnjoyTripBackend.dto.place.MyPlaceResponseDto;
import com.example.EnjoyTripBackend.dto.place.PlaceRequestDto;
import com.example.EnjoyTripBackend.dto.place.PlaceResponseDto;
import com.example.EnjoyTripBackend.dto.place.PlaceSearchwordRequestDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;@Service
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
        long totalCount = placeRepository.findTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());
        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .build();
        return ResponseResult.of("여행 관광지 게시글 목록입니다.", placeRepository.findAll(requestList), totalPages);
    }

    public ResponseResult<List<Optional<PlaceResponseDto>>> findSearchWordPlaces(PlaceSearchwordRequestDto placeSearchwordRequestDto, Pageable pageable){
        PageRequestList<?> requestList = PageRequestList.builder()
                .data(placeSearchwordRequestDto)
                .pageable(pageable)
                .build();
        List<Optional<PlaceResponseDto>> searchWordPlaces = placeRepository.findSearchWordPlaces(requestList);
        int size = searchWordPlaces.size();
        int totalPages = size / pageable.getPageSize();
        return ResponseResult.of("검색어 관련 여행 관광지 게시글 목록입니다.", searchWordPlaces, totalPages);
    }

    public NonPagingResponseResult<PlaceResponseDto> fineOne(Long id) {
        PlaceResponseDto placeResponseDto = placeRepository.findOne(id).orElseThrow(() -> new EnjoyTripException(ErrorCode.CONTENT_NOT_FOUNT));
        return NonPagingResponseResult.of("여행 관광지 게시글 상세보기 입니다.", placeResponseDto);
    }

    public PlaceResponseDto findById(Long id) {
        return placeRepository.findOne(id).orElseThrow(() -> new EnjoyTripException(ErrorCode.CONTENT_NOT_FOUNT));
    }

    @Transactional
    public Long updateBlog(Long id, PlaceRequestDto placeRequestDto) {
        return placeRepository.updateBlog(id, placeRequestDto);
    }

    @Transactional
    public Long deleteBlog(Long id) {
        return placeRepository.deleteBlog(id);
    }

    public NonPagingResponseResult<List<Optional<MyPlaceResponseDto>>> myPage(String userEmail) {
        return NonPagingResponseResult.of("작성한 블로그 글 입니다.", placeRepository.myPage(userEmail));
    }
}