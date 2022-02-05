package com.tistory.hitomis.springboot_practice1.service;

import com.tistory.hitomis.springboot_practice1.code.StatusCode;
import com.tistory.hitomis.springboot_practice1.dto.CreateDeveloper;
import com.tistory.hitomis.springboot_practice1.dto.DeveloperDetailDto;
import com.tistory.hitomis.springboot_practice1.dto.DeveloperDto;
import com.tistory.hitomis.springboot_practice1.dto.EditDeveloper;
import com.tistory.hitomis.springboot_practice1.entity.Developer;
import com.tistory.hitomis.springboot_practice1.entity.RetiredDeveloper;
import com.tistory.hitomis.springboot_practice1.exception.CustomException;
import com.tistory.hitomis.springboot_practice1.repository.DeveloperRepository;
import com.tistory.hitomis.springboot_practice1.repository.RetiredDeveloperRepository;
import com.tistory.hitomis.springboot_practice1.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.tistory.hitomis.springboot_practice1.exception.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

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
                .statusCode(StatusCode.EMPLOYED)
                .memberId(request.getMemberId())
                .build();

        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {

        validateDeveloperLevel(
                request.getDeveloperLevel(),
                request.getExperienceYears()
        );

        /*
        Optional<Developer> byMemberId = developerRepository.findByMemberId(request.getMemberId());
        if (byMemberId.isPresent()) throw new CustomException(DUPLICATED_MEMBER_ID);
        */
        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new CustomException(DUPLICATED_MEMBER_ID);
                }));

    }

    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository.findDeveloperByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new CustomException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId,
                                            EditDeveloper.Request request) {

        // validation check
        validateDeveloperLevel(
                request.getDeveloperLevel(),
                request.getExperienceYears()
        );

        Developer developer = developerRepository.findByMemberId(memberId).orElseThrow(
                () -> new CustomException(NO_DEVELOPER)
        );

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        // repository.save가 없어도 db에 저장이 되네?
//        developerRepository.save(developer);
        return DeveloperDetailDto.fromEntity(developer);

    }

    /**
     * 공통 validation level 함수
     *
     * @param developerLevel
     * @param experienceYears
     */
    private void validateDeveloperLevel(DeveloperLevel developerLevel,
                                        Integer experienceYears) {
        if (developerLevel == DeveloperLevel.SENIOR
                && experienceYears < 10) {
            throw new CustomException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNGNIOR
                && (experienceYears < 4 || experienceYears > 10)) {
            throw new CustomException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4) {
            throw new CustomException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        // 1. statuscode : EMPLOYED -> RETIRED
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomException(NO_DEVELOPER));
        developer.setStatusCode(StatusCode.RETIRED);

        // 2. save into RETIRED
        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();
        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }
}
