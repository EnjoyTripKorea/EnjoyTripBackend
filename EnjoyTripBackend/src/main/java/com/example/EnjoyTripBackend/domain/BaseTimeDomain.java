package com.example.EnjoyTripBackend.domain;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public abstract class BaseTimeDomain {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}