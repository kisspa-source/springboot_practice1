package com.tistory.hitomis.springboot_practice1.repository;

import com.tistory.hitomis.springboot_practice1.code.StatusCode;
import com.tistory.hitomis.springboot_practice1.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author hitomis
 */
@Repository
public interface DeveloperRepository
        extends JpaRepository<Developer, Long> {

    Optional<Developer> findByMemberId(String memberId);

    List<Developer> findDeveloperByStatusCodeEquals(StatusCode statusCode);
}
