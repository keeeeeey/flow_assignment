package com.flow.assignment.service;

import com.flow.assignment.common.exception.BusinessException;
import com.flow.assignment.common.exception.ErrorCode;
import com.flow.assignment.dto.request.CreateCustomExtensionRequest;
import com.flow.assignment.dto.response.CustomExtensionResponse;
import com.flow.assignment.entity.CustomExtension;
import com.flow.assignment.entity.enumerated.FixedExtensionType;
import com.flow.assignment.repository.CustomExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CustomExtensionService {

    private static final long MAX_CUSTOM_EXTENSION_COUNT = 200;

    private final CustomExtensionRepository repository;

    @Transactional
    public CustomExtensionResponse create(CreateCustomExtensionRequest request) {
        validateCreatableExtension(request.getName());

        CustomExtension customExtension = CustomExtension.builder()
                .name(request.getName())
                .build();

        repository.save(customExtension);
        return CustomExtensionResponse.fromEntity(customExtension);
    }

    private void validateCreatableExtension(String name) {
        // 커스텀 확장자 개수 체크
        if (repository.countAllBy() >= MAX_CUSTOM_EXTENSION_COUNT) {
            throw new BusinessException(ErrorCode.EXCEED_TOTAL_CUSTOM_COUNT);
        }

        // 고정 확장자 추가 시도 예외처리
        if (isFixedExtension(name)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_FIXED_EXTENSION);
        }

        // 커스텀 확장자 중복체크
        if (repository.existsByName(name)) {
            throw new BusinessException(ErrorCode.ALREADY_CREATED_EXTENSION);
        }
    }

    private boolean isFixedExtension(String name) {
        return FixedExtensionType.getNames().stream()
                .anyMatch(fixedName -> fixedName.equalsIgnoreCase(name));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(ErrorCode.ALREADY_DELETED_EXTENSION);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CustomExtensionResponse> get() {
        return repository.findAll().stream()
                .map(CustomExtensionResponse::fromEntity)
                .collect(toList());
    }
}
