package com.tistory.hitomis.springboot_practice1.dto;

import com.tistory.hitomis.springboot_practice1.exception.CustomErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {
    private CustomErrorCode errorCode;
    private String errorMessage;
}
