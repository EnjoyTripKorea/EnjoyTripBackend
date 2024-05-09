package com.example.EnjoyTripBackend.dto.member;

import com.example.EnjoyTripBackend.domain.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*\\p{Punct}).{2,20}$", message = "영문/숫자/특수문자 2~20자 이내로 작성해주세요")
    private String password;

    @NotNull
    private Integer age;

    @NotNull
    private Gender gender;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{11}")
    private String phoneNumber;

}