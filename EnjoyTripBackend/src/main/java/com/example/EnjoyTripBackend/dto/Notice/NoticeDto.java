package com.example.EnjoyTripBackend.dto.Notice;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeDto {

    @NotEmpty
    private String category;

    @NotEmpty
    private String title;

    private String imgUrl;

    @NotEmpty
    private String content;

}