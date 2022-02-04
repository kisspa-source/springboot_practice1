package com.tistory.hitomis.springboot_practice1.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hitomis
 */
@AllArgsConstructor
@Getter
public enum DeveloperLevel {
    NEW("신입 개발자"),
    JUNIOR("주니어 개발자"),
    JUNGNIOR("중니어 개발자"),
    SENIOR("시니어 개발자")
    ;

    private final String description;
}