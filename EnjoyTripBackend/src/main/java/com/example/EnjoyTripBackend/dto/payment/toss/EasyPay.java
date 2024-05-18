package com.example.EnjoyTripBackend.dto.payment.toss;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EasyPay {
    private String provider;
    private int amount;
    private int discountAmount;
}