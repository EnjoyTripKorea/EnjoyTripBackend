package com.example.EnjoyTripBackend.dto.payment.toss;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private int installmentPlanMonths;
    private boolean isInterestFree;
    private String interestPayer;
    private String approveNo;
    private boolean useCardPoint;
    private String cardType;
    private String ownerType;
    private String acquireStatus;
    private int amount;
}