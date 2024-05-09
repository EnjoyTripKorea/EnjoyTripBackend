package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Member;
import com.example.EnjoyTripBackend.domain.MemberRole;
import com.example.EnjoyTripBackend.dto.member.SignUpRequestDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.repository.MemberRepository;
import com.example.EnjoyTripBackend.util.Encrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final Encrypt encrypt;

    @Transactional
    public Long signUp(SignUpRequestDto signUpRequestDto) {

        Optional<Member> existMember = memberRepository.findByEmail(signUpRequestDto.getEmail());
        if (existMember.isPresent()){
            throw new EnjoyTripException(ErrorCode.DUPLICATE_EMAIL);
        }

        String salt = encrypt.getSalt();
        String encodedPassword = encrypt.encode(signUpRequestDto.getPassword(), salt);

        Member member = Member.builder()
                .email(signUpRequestDto.getEmail())
                .username(signUpRequestDto.getUsername())
                .password(encodedPassword)
                .salt(salt)
                .age(signUpRequestDto.getAge())
                .gender(signUpRequestDto.getGender())
                .memberRole(MemberRole.CUSTOMER)
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .build();

        return memberRepository.save(member);
    }
}
