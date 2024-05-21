package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Camping;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.ResponseResult;
import com.example.EnjoyTripBackend.dto.camping.CampingResponseDto;
import com.example.EnjoyTripBackend.repository.CampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampingService {

    private final CampingRepository campingRepository;

    @Transactional
    public Long save(Camping camping) {
        return campingRepository.save(camping);
    }

    public ResponseResult<List<CampingResponseDto>> findAll(Pageable pageable) {
        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .build();

        long totalCount = campingRepository.findTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());
        return ResponseResult.of("캠핑 정보 게시글 목록입니다.", campingRepository.findAll(requestList),totalPages);
    }
}