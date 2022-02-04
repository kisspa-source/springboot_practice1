package com.tistory.hitomis.springboot_practice1.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private CustomErrorCode customErrorCode;
    private String detailMessage;

    public CustomException(CustomErrorCode errorCode) {
        super(errorCode.getMessage());
        this.customErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public CustomException(CustomErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.customErrorCode = errorCode;
        this.detailMessage = getMessage();
    }
}
