package com.example.EnjoyTripBackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일 계정입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED,"아이디 또는 비밀번호가 잘못 되었습니다."),
    ANONYMOUS_USER(HttpStatus.UNAUTHORIZED,"인증되지 않는 사용자입니다. 로그인을 진행해 주세요"),
    REQUEST_METHOD_NOT_ALLOW(HttpStatus.METHOD_NOT_ALLOWED,"올바르지 않는 http method 입니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 공지사항입니다."),
    FILE_UPLOAD_NOT_WORKING(HttpStatus.BAD_REQUEST,"파일 업로드 실패하였습니다."),
    CONTENT_NOT_FOUNT(HttpStatus.NOT_FOUND, "찾고자 하는 글이 없습니다."),
    FAIL_INSERT_GOLF_DATA(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스에 골프 데이터 저장에 실패하였습니다"),
    FAIL_COMMUNICATE_TOSSPAYMENT_API(HttpStatus.INTERNAL_SERVER_ERROR,"토스페이먼트 API와의 통신에 실패하였습니다."),
    FAIL_COMMUNICATE_EXTERNAL_API(HttpStatus.INTERNAL_SERVER_ERROR,"외부 API와의 통신에 실패하였습니다.");

    private final HttpStatus httpstatus;
    private final String message;
}