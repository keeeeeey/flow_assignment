package com.flow.assignment.repository;

import com.flow.assignment.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    Long countAllBy();

    boolean existsByName(String name);
}
