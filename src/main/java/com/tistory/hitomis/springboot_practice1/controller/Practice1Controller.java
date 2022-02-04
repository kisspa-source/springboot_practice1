package com.tistory.hitomis.springboot_practice1.controller;

import com.tistory.hitomis.springboot_practice1.dto.CreateDeveloper;
import com.tistory.hitomis.springboot_practice1.dto.DeveloperDto;
import com.tistory.hitomis.springboot_practice1.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hitomis
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class Practice1Controller {
    private final DeveloperService developerService;

    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDeveloper(
            @Valid @RequestBody CreateDeveloper.Request request
    ) {
        log.info("request : {}", request);

        return developerService.createDeveloper(request);
    }

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        // GET /developers HTTP/1.1
        log.info("GET /developers HTTP/1.1");

        return developerService.getAllDevelopers();
    }
}
