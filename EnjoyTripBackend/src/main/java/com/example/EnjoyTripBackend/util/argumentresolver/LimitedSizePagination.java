package com.example.EnjoyTripBackend.util.argumentresolver;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LimitedSizePagination {
    int maxSize() default 2000;
}