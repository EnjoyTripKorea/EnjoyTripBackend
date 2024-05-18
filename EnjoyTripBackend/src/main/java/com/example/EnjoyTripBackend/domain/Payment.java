package com.example.EnjoyTripBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseTimeDomain{

    private Long id;
    private String paymentKey;
    private String userEmail;
    private String orderName;
    private int taxExemptionAmount;
    private String orderId;
    private String status;
    private String country;
    private String receipt;
    private String currency;
    private int totalAmount;
    private int balanceAmount;
    private int suppliedAmount;
    private int vat;
    private int taxFreeAmount;
}