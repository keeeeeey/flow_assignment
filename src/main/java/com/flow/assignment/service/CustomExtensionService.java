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

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CustomExtensionService {

    private final CustomExtensionRepository repository;

    @Transactional
    public String create(CreateCustomExtensionRequest request) {
        // 커스텀 확장자 개수 체크
        Long count = repository.countAllBy();
        if (count > 199) throw new BusinessException(ErrorCode.EXCEED_TOTAL_CUSTOM_COUNT);

        // 고정 확장자 추가 시도 예외처리
        boolean isFixed = Arrays.stream(FixedExtensionType.values())
                .anyMatch(type -> type.name().equalsIgnoreCase(request.getName()));
        if (isFixed) throw new BusinessException(ErrorCode.FORBIDDEN_FIXED_EXTENSION);

        // 커스텀 확장자 중복체크
        boolean isExist = repository.existsByName(request.getName());
        if (isExist) throw new BusinessException(ErrorCode.ALREADY_CREATED_EXTENSION);

        // 커스텀 확장자 저장
        CustomExtension customExtension = CustomExtension.builder()
                .name(request.getName())
                .build();
        repository.save(customExtension);
        return request.getName();
    }

    @Transactional
    public void delete(Long id) {
        CustomExtension customExtension = repository.findByIdOrElseThrow(id);
        repository.delete(customExtension);
    }

    @Transactional(readOnly = true)
    public List<CustomExtensionResponse> get() {
        List<CustomExtension> customExtensions = repository.findAll();
        return customExtensions
                .stream()
                .map(CustomExtensionResponse::fromEntity)
                .collect(toList());
    }
}
