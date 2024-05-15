package com.example.EnjoyTripBackend.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResponseDto {

    private String msg;
    private List<BoardDto> data;

}
