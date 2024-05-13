package com.example.EnjoyTripBackend.dto.Board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@NoArgsConstructor
public class BoardDto {

    @NotNull
    private Long memberId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

}