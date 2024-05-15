package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Notice;
import com.example.EnjoyTripBackend.dto.Notice.NoticeDto;
import com.example.EnjoyTripBackend.dto.Notice.NoticeListResponseDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public NoticeListResponseDto findAll(){
        NoticeListResponseDto responseDto = new NoticeListResponseDto();
        responseDto.setData(noticeRepository.findAll());
        if (responseDto.getData().isEmpty()) {
            responseDto.setMsg("공지사항이 없습니다.");
        } else {
            responseDto.setMsg("공지사항 게시글 목록입니다.");
        }
        return responseDto;
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