package com.example.EnjoyTripBackend.controller;

import com.example.EnjoyTripBackend.dto.NonPagingResponseResult;
import com.example.EnjoyTripBackend.dto.payment.toss.MyPaymentHistoryDto;
import com.example.EnjoyTripBackend.service.PaymentService;
import com.example.EnjoyTripBackend.util.argumentresolver.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/toss/success")
    public ResponseEntity<String> successPay(@RequestParam("orderId") String orderId, @RequestParam("paymentKey") String paymentKey,
                                                       @RequestParam("amount") long amount , @SessionUser String userEmail) {
        paymentService.payment(orderId, paymentKey, amount, userEmail);
        return ResponseEntity.ok().body("<!DOCTYPE html> <html lang=\"en\"> <head><meta charset=\"UTF-8\"><meta http-equiv=\"refresh\" content=\"0.1;url=http://localhost:5173/api/payment/myPage\"><title>Redirecting...</title></head><body><p>Redirecting to <a href=\"http://localhost:5173/api/payment/myPage\">http://localhost:5173/api/payment/myPage</a></p></body></html>"
        );
    }

    @GetMapping("/payment")
    public ResponseEntity<NonPagingResponseResult<List<MyPaymentHistoryDto>>> myPaymentPage(@SessionUser String userEmail){
        return ResponseEntity.ok().body(paymentService.myPaymentPage(userEmail));
    }
}