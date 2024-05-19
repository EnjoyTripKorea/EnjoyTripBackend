package com.example.EnjoyTripBackend.dto.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {
    private Long id;
    private String category;
    private String title;
    private String content;
    private String imgUrl;
    private String createdDate;
    private String modifiedDate;
}