package com.tistory.hitomis.springboot_practice1.service;

import com.tistory.hitomis.springboot_practice1.dto.DeveloperDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeveloperServiceTest {

    @Autowired
    private DeveloperService developerService;

    @Test
    public void testSometing() {
        List<DeveloperDto> allEmployedDevelopers = developerService.getAllEmployedDevelopers();
        System.out.println(allEmployedDevelopers);
    }
}