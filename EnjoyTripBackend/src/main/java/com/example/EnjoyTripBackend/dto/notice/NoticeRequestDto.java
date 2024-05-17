package com.example.EnjoyTripBackend.dto.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeRequestDto {
    private String category;
    private String title;
    private String content;
    private String imgUrl;

}