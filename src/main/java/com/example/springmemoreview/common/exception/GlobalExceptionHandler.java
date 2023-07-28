package com.example.springmemoreview.common.exception;

import com.example.springmemoreview.common.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@RestControllerAdvice // Controller 에서 발생하는 모든 예외 처리 가능하도록
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ApiResponseDto restApiException = new ApiResponseDto(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponseDto restApiException = new ApiResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({RejectedExecutionException.class})
    public ResponseEntity<ApiResponseDto> handleRejectedExecutionException(RejectedExecutionException ex) {
        ApiResponseDto restApiException = new ApiResponseDto(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ApiResponseDto> handleNullPointerException(NullPointerException ex) {
        ApiResponseDto restApiException = new ApiResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }
}
