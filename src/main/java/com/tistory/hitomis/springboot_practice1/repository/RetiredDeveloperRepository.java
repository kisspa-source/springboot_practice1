package com.tistory.hitomis.springboot_practice1.repository;

import com.tistory.hitomis.springboot_practice1.entity.Developer;
import com.tistory.hitomis.springboot_practice1.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author hitomis
 */
@Repository
public interface RetiredDeveloperRepository
        extends JpaRepository<RetiredDeveloper, Long> {

}
