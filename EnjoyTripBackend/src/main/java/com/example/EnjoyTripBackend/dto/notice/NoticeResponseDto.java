package com.example.EnjoyTripBackend.dto.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {
    private Long id;
    private String category;
    private String title;
    private String content;
    private String imgUrl;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
}