package com.tistory.hitomis.springboot_practice1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomErrorCode {
    NO_DEVELOPER("해당되는 개발자가 없습니다."),
    DUPLICATED_MEMBER_ID("MemberId가 중복되는 개발자가 있습니다."),
    LEVEL_EXPERIENCE_YEARS_NOT_MATCHED("개발자 레벨과 연차가 맞지 않습니다."),

    INTERNAL_SERVER_ERROR("서버에 오류가 발생하였습니다."),
    INVALID_REQUEST("잘못된 요청입니다.")
    ;

    private final String message;
}
