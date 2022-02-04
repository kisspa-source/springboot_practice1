package com.tistory.hitomis.springboot_practice1.service;

import com.tistory.hitomis.springboot_practice1.dto.CreateDeveloper;
import com.tistory.hitomis.springboot_practice1.dto.DeveloperDto;
import com.tistory.hitomis.springboot_practice1.entity.Developer;
import com.tistory.hitomis.springboot_practice1.exception.CustomException;
import com.tistory.hitomis.springboot_practice1.exception.CustomException;
import com.tistory.hitomis.springboot_practice1.repository.DeveloperRepository;
import com.tistory.hitomis.springboot_practice1.type.DeveloperLevel;
import com.tistory.hitomis.springboot_practice1.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tistory.hitomis.springboot_practice1.exception.CustomErrorCode.DUPLICATED_MEMBER_ID;
import static com.tistory.hitomis.springboot_practice1.exception.CustomErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {

        // validation check
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .name(request.getName())
                .age(request.getAge())
                .memberId(request.getMemberId())
                .build();

        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYears = request.getExperienceYears();

        if (developerLevel == DeveloperLevel.SENIOR
                && experienceYears < 10) {
            throw new CustomException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNGNIOR
                && experienceYears < 4 || experienceYears > 10) {
            throw new CustomException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4) {
            throw new CustomException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        /*
        Optional<Developer> byMemberId = developerRepository.findByMemberId(request.getMemberId());
        if (byMemberId.isPresent()) throw new CustomException(DUPLICATED_MEMBER_ID);
        */
        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new CustomException(DUPLICATED_MEMBER_ID);
                }));

    }

    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }
}
