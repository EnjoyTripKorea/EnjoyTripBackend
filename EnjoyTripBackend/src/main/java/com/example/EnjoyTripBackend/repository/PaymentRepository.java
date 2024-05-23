package com.example.EnjoyTripBackend.repository;

import com.example.EnjoyTripBackend.domain.Payment;
import com.example.EnjoyTripBackend.dto.payment.toss.MyPaymentHistoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentRepository {
    Long save(Payment Payment);
    List<MyPaymentHistoryDto> findAllPaymentHistory(String email);
}