package com.tistory.hitomis.springboot_practice1.controller;

import com.tistory.hitomis.springboot_practice1.dto.CreateDeveloper;
import com.tistory.hitomis.springboot_practice1.dto.DeveloperDetailDto;
import com.tistory.hitomis.springboot_practice1.dto.DeveloperDto;
import com.tistory.hitomis.springboot_practice1.dto.EditDeveloper;
import com.tistory.hitomis.springboot_practice1.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 생성
     *
     * @param request
     * @return
     */
    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDeveloper(
            @Valid @RequestBody CreateDeveloper.Request request
    ) {
        log.info("request : {}", request);

        return developerService.createDeveloper(request);
    }

    /**
     * 상세 조회
     *
     * @param memberId
     * @return
     */
    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable String memberId
    ) {
        return developerService.getDeveloperDetail(memberId);
    }

    /**
     * 전체 목록
     *
     * @return
     */
    @GetMapping("/developers")
    public List<DeveloperDto> getAllEmployedDevelopers() {
        // GET /developers HTTP/1.1
        log.info("GET /developers HTTP/1.1");

        return developerService.getAllEmployedDevelopers();
    }

    /**
     * 수정
     *
     * @param memberId
     * @param request
     * @return
     */
    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request
    ) {
        log.info("PUT /developer HTTP/1.1");

        return developerService.editDeveloper(memberId, request);
    }

    @DeleteMapping("/developer/{memberId}")
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable String memberId
    ) {
        developerService.deleteDeveloper(memberId);
        return null;
    }
}
