package com.example.EnjoyTripBackend.dto.payment.toss;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResultDto {
    private String mId;
    private String lastTransactionKey;
    private String paymentKey;
    private String orderId;
    private String orderName;
    private int taxExemptionAmount;
    private String status;
    private Date requestedAt;
    private Date approvedAt;
    private boolean useEscrow;
    private boolean cultureExpense;
    private CardInfo card;
    private String secret;
    private String type;
    private EasyPay easyPay;
    private String country;
    private ReceiptInfo receipt;
    private Checkout checkout;
    private String currency;
    private int totalAmount;
    private int balanceAmount;
    private int suppliedAmount;
    private int vat;
    private int taxFreeAmount;
    private String method;
    private String version;
}