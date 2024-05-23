package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Payment;
import com.example.EnjoyTripBackend.dto.NonPagingResponseResult;
import com.example.EnjoyTripBackend.dto.payment.toss.MyPaymentHistoryDto;
import com.example.EnjoyTripBackend.dto.payment.toss.PaymentResultDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    @Value("${toss.secret_key}")
    private String secret_key;

    @Value("${toss.targetUrl}")
    private String targetUrl;

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ObjectMapper objectMapper;

    @Transactional
    public String payment(String orderId, String paymentKey, long amount, String userEmail) {

        ResponseEntity<String> response = requestTossPayment(orderId, paymentKey, amount);

        try {
            PaymentResultDto paymentResultDto = objectMapper.readValue(response.getBody(), PaymentResultDto.class);
            savePayment(userEmail, paymentResultDto);
            return "결제완료";
        } catch (JsonProcessingException e) {
            throw new EnjoyTripException(ErrorCode.FAIL_COMMUNICATE_TOSSPAYMENT_API);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private long savePayment(String userEmail, PaymentResultDto paymentResultDto) {
        Payment payment = Payment.builder()
                .paymentKey(paymentResultDto.getPaymentKey())
                .userEmail(userEmail)
                .orderName(paymentResultDto.getOrderName())
                .taxExemptionAmount(paymentResultDto.getTaxExemptionAmount())
                .orderId(paymentResultDto.getOrderId())
                .status(paymentResultDto.getStatus())
                .country(paymentResultDto.getCountry())
                .receipt(paymentResultDto.getReceipt().getUrl())
                .currency(paymentResultDto.getCurrency())
                .totalAmount(paymentResultDto.getTotalAmount())
                .balanceAmount(paymentResultDto.getBalanceAmount())
                .suppliedAmount(paymentResultDto.getSuppliedAmount())
                .vat(paymentResultDto.getVat())
                .taxFreeAmount(paymentResultDto.getTaxFreeAmount())
                .build();
        System.out.println(payment.getPaymentKey());
        return paymentRepository.save(payment);
    }

    private ResponseEntity<String> requestTossPayment(String orderId, String paymentKey, long amount) {
        String encodedAuthKey = new String(Base64.getEncoder().encode((secret_key + ":").getBytes(StandardCharsets.UTF_8)));
        httpHeaders.setBasicAuth(encodedAuthKey);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        jsonObject.put("paymentKey", paymentKey);
        jsonObject.put("amount", amount);

        HttpEntity<String> requestHttpEntity = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        return restTemplate.exchange(targetUrl, HttpMethod.POST, requestHttpEntity, String.class);
    }

    public NonPagingResponseResult<List<MyPaymentHistoryDto>> myPaymentPage(String userEmail) {
        List<MyPaymentHistoryDto> myPaymentHistoryDto = paymentRepository.findAllPaymentHistory(userEmail);
        return NonPagingResponseResult.of("나의 결제정보 데이터입니다.", myPaymentHistoryDto);
    }
}