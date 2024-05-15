package com.example.EnjoyTripBackend.dto.Notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResponseDto {

    private String msg;
    private List<NoticeDto> data;

}
