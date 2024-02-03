package com.example.flow.repository;

import com.example.flow.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogRepository extends JpaRepository<Log, Long> {
    Optional<Log> findByName(String name);
}
