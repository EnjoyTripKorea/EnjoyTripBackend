package com.example.EnjoyTripBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender  {
    MAN("남자"), WOMAN("여자");

    private String value;
}