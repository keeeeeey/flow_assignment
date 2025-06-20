package com.flow.assignment.repository;

import com.flow.assignment.common.exception.BusinessException;
import com.flow.assignment.common.exception.ErrorCode;
import com.flow.assignment.entity.FixedExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FixedExtensionRepository extends JpaRepository<FixedExtension, Long> {
    default FixedExtension findByNameOrElseThrow(String name) {
        return findByName(name).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }
    boolean existsByName(String name);
    Optional<FixedExtension> findByName(String name);
}
