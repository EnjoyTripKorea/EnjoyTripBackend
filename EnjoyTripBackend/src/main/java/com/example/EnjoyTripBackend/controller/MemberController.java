package com.example.EnjoyTripBackend.controller;

import com.example.EnjoyTripBackend.dto.member.SignUpRequestDto;
import com.example.EnjoyTripBackend.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto, BindingResult bindingResult){
        memberService.signUp(signUpRequestDto);
        return ResponseEntity.ok().body("회원가입 완료");
    }
}