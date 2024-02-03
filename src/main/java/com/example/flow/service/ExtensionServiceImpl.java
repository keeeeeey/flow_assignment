package com.example.flow.service;

import com.example.flow.common.exception.ApiException;
import com.example.flow.common.exception.ExceptionEnum;
import com.example.flow.dto.request.ExtensionRequestDto;
import com.example.flow.dto.response.ExtensionListResponseDto;
import com.example.flow.dto.response.ExtensionResponseDto;
import com.example.flow.entity.Extension;
import com.example.flow.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtensionServiceImpl implements ExtensionService {

    private final ExtensionRepository extensionRepository;

    private final LogService logService;

    @Override
    @Transactional
    public Long createExtension(ExtensionRequestDto requestDto) {
        Optional<Extension> extensionOptional = extensionRepository.findByName(requestDto.getName());
        if (extensionOptional.isPresent()) throw new ApiException(ExceptionEnum.EXTENSION_DUPLICATE_EXCEPTION);

        Extension extension = makeExtension(requestDto.getName());

        Extension saveExtension = extensionRepository.save(extension);
        logSave(requestDto.getName(), "post");
        return saveExtension.getId();
    }

    @Override
    @Transactional
    public void deleteExtension(String extensionName) {
        Optional<Extension> extensionOptional = extensionRepository.findByName(extensionName);
        if (extensionOptional.isEmpty()) throw new ApiException(ExceptionEnum.EXTENSION_NOT_EXIST_EXCEPTION);
        extensionRepository.deleteByName(extensionName);
        logSave(extensionName, "delete");
    }

    @Override
    @Transactional(readOnly = true)
    public ExtensionListResponseDto findAllExtension() {
        List<Extension> allExtension = extensionRepository.findAll();
        List<ExtensionResponseDto> fixExtensionList = new ArrayList<>();
        List<ExtensionResponseDto> extensionList = new ArrayList<>();

        allExtension.forEach(e -> {
            ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(e.getId(), e.getName());
            if (e.isFixExtension()) {
                fixExtensionList.add(extensionResponseDto);
            } else {
                extensionList.add(extensionResponseDto);
            }
        });

        return ExtensionListResponseDto.of(fixExtensionList, extensionList);
    }

    private Extension makeExtension(String extensionName) {
        if (isFixExtension(extensionName)) return Extension.of(extensionName, true);
        Long countCustomExtension = extensionRepository.countByIsFixExtensionIsFalse();
        if (countCustomExtension >= 2) throw new ApiException(ExceptionEnum.MAX_EXTENSION_COUNT_OVER_EXCEPTION);
        return Extension.of(extensionName, false);
    }

    private void logSave(String extensionName, String method) {
        try {
            logService.logSave(extensionName, method);
        } catch (Exception e) {
            log.info("log 저장에 실패했습니다. name = {}, method = {}", extensionName, method);
            log.info("정상 흐름 반환");
        }
    }

    private boolean isFixExtension(String name) {
        return name.equals("bat") || name.equals("cmd") || name.equals("com") ||
                name.equals("cpl") || name.equals("exe") || name.equals("scr") || name.equals("js");
    }
}
