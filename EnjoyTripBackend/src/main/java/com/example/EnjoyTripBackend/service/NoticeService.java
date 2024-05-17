package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Notice;
import com.example.EnjoyTripBackend.dto.PageRequestList;
import com.example.EnjoyTripBackend.dto.ResponseResult;
import com.example.EnjoyTripBackend.dto.notice.NoticeDto;
import com.example.EnjoyTripBackend.dto.notice.NoticeResponseDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeDto noticeDto) {
        Notice notice = Notice.builder()
                .category(noticeDto.getCategory())
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .imgUrl(noticeDto.getImgUrl())
                .build();
        return noticeRepository.save(notice);
    }

    public NoticeDto findById(Long id) {
        return noticeRepository.findById(id).orElseThrow(()-> new EnjoyTripException(ErrorCode.NOTICE_NOT_FOUND));
    }

    public ResponseResult<List<NoticeResponseDto>> findAll(Pageable pageable) {
        long totalCount = noticeRepository.findTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());

        PageRequestList<?> requestList = PageRequestList.builder()
                .pageable(pageable)
                .build();
        return ResponseResult.of("공지사항 게시글 목록입니다.", noticeRepository.findAll(requestList), totalPages);
    }

    @Transactional
    public void update(Long id, NoticeDto noticeDto) {
        noticeRepository.update(id, noticeDto);
    }

    @Transactional
    public void delete(Long id){
        noticeRepository.delete(id);
    }
}