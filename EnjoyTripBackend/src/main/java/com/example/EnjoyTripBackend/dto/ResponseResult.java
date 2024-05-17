package com.example.EnjoyTripBackend.dto;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ResponseResult<T> {
    private final String msg;
    private final T data;
    private final int totalPages;
}