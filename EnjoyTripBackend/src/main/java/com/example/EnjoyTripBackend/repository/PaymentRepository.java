package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
    Long save(Payment Payment);
}