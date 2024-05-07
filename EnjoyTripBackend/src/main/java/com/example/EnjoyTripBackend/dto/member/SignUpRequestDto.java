package com.example.EnjoyTripBackend.dto.member;

import com.example.EnjoyTripBackend.domain.Gender;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    private String email;
    private String username;
    private String password;
    private Integer age;
    private Gender gender;
    private String phoneNumber;

}
