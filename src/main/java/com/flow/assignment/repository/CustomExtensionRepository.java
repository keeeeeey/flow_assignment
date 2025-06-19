package com.flow.assignment.repository;

import com.flow.assignment.common.exception.BusinessException;
import com.flow.assignment.common.exception.ErrorCode;
import com.flow.assignment.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    default CustomExtension findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    Long countAllBy();
}
