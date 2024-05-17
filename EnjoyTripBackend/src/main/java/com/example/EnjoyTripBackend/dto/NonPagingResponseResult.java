package com.example.EnjoyTripBackend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class NonPagingResponseResult<T> {
    private final String msg;
    private final T data;
}