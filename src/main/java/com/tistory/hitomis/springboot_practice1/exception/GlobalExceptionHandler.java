package com.tistory.hitomis.springboot_practice1.exception;

import com.tistory.hitomis.springboot_practice1.dto.CustomErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.tistory.hitomis.springboot_practice1.exception.CustomErrorCode.INTERNAL_SERVER_ERROR;
import static com.tistory.hitomis.springboot_practice1.exception.CustomErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CustomErrorResponse handlerException(
            CustomException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getCustomErrorCode(), request.getRequestURI(), e.getDetailMessage());
        return CustomErrorResponse.builder()
                .errorCode(e.getCustomErrorCode())
                .errorMessage(e.getDetailMessage()).build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public CustomErrorResponse handleBadRequest(
            CustomException e,
            HttpServletRequest request
    ) {
        log.error("Global Exception notsup, not valid url: {}, message: {}",
                request.getRequestURI(), e.getDetailMessage());
        return CustomErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

    @ExceptionHandler(value = {Exception.class})
    public CustomErrorResponse handleException(
            CustomException e,
            HttpServletRequest request
    ) {
        log.error("Global Exception url: {}, message: {}",
                request.getRequestURI(), e.getDetailMessage());
        return CustomErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
