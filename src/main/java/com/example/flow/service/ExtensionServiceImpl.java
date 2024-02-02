package com.example.flow.service;

import com.example.flow.common.exception.ApiException;
import com.example.flow.common.exception.ExceptionEnum;
import com.example.flow.dto.request.ExtensionRequestDto;
import com.example.flow.dto.response.ExtensionListResponseDto;
import com.example.flow.dto.response.ExtensionResponseDto;
import com.example.flow.entity.Extension;
import com.example.flow.entity.Log;
import com.example.flow.repository.ExtensionRepository;
import com.example.flow.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtensionServiceImpl implements ExtensionService {

    private final ExtensionRepository extensionRepository;

    private final LogRepository logRepository;

    @Override
    @Transactional
    public Long createExtension(ExtensionRequestDto requestDto) {
        Optional<Extension> extensionOptional = extensionRepository.findByName(requestDto.getName());
        if (extensionOptional.isPresent()) throw new ApiException(ExceptionEnum.EXTENSION_DUPLICATE_EXCEPTION);

        Extension extension;
        boolean fixExtensionFlag = isFixExtension(requestDto.getName());
        if (!fixExtensionFlag) {
            Long countCustomExtension = extensionRepository.countByIsFixExtensionIsFalse();
            if (countCustomExtension >= 2)
                throw new ApiException(ExceptionEnum.MAX_EXTENSION_COUNT_OVER_EXCEPTION);

            extension = Extension.of(requestDto, false);
        } else {
            extension = Extension.of(requestDto, true);
        }

        Extension saveExtension = extensionRepository.save(extension);
        try {
            logSave(requestDto.getName(), "post");
        } catch (Exception e) {
            log.info("log 저장에 실패했습니다. name = {}, method = {}", requestDto.getName(), "post");
            log.info("정상 흐름 반환");
        }
        return saveExtension.getId();
    }

    @Override
    @Transactional
    public void deleteExtension(String extensionName) {
        Optional<Extension> extensionOptional = extensionRepository.findByName(extensionName);
        if (extensionOptional.isEmpty()) throw new ApiException(ExceptionEnum.EXTENSION_NOT_EXIST_EXCEPTION);
        extensionRepository.deleteByName(extensionName);
        try {
            logSave(extensionName, "delete");
        } catch (Exception e) {
            log.info("log 저장에 실패했습니다. name = {}, method = {}", extensionName, "delete");
            log.info("정상 흐름 반환");
        }
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logSave(String extensionName, String method) {
        Log logData = Log.of(extensionName, method);
        logRepository.save(logData);
    }

    private boolean isFixExtension(String name) {
        return name.equals("bat") || name.equals("cmd") || name.equals("com") ||
                name.equals("cpl") || name.equals("exe") || name.equals("scr") || name.equals("js");
    }
}
