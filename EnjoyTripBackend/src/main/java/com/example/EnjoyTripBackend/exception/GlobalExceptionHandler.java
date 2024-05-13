package com.example.EnjoyTripBackend.exception;

import com.example.EnjoyTripBackend.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EnjoyTripException.class)
    public ResponseEntity<String> EnjoyTripExceptionHandler(EnjoyTripException e) {
        log.error(e.getMessage());
        return error(e);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(METHOD_NOT_ALLOWED.value()).body(ErrorCode.REQUEST_METHOD_NOT_ALLOW.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> EnjoyTripExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        return serverError();
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationApiException(CustomValidationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> error(EnjoyTripException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpstatus()).body(e.getMessage());
    }

    private ResponseEntity<String> serverError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }
}