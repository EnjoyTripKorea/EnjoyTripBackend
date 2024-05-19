package com.example.EnjoyTripBackend.util;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LimitedSizePagination {
    int maxSize() default 2000;
}