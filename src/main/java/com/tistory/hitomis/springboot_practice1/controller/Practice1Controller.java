package com.tistory.hitomis.springboot_practice1.controller;

import com.tistory.hitomis.springboot_practice1.dto.CreateDeveloper;
import com.tistory.hitomis.springboot_practice1.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author hitomis
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class Practice1Controller {
    private final DeveloperService developerService;

    @GetMapping("/developers")
    public List<String> getAllDevelopers() {
        // GET /developers HTTP/1.1
        log.info("GET /developers HTTP/1.1");

        return Arrays.asList("aa", "bb", "cc");
    }

    @PostMapping("/create-developer")
    public List<String> createDeveloper(
            @Valid @RequestBody CreateDeveloper.Request request
    ) {
        log.info("request : {}", request);

        developerService.createDeveloper(request);

        return Collections.singletonList("sdf");
    }
}
