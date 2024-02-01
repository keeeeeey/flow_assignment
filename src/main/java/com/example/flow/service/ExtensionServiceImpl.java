package com.example.flow.service;

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

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtensionServiceImpl implements ExtensionService {

    private final ExtensionRepository extensionRepository;

    @Override
    @Transactional
    public Long createExtension(ExtensionRequestDto requestDto) {
        Extension extension = Extension.builder()
                .name(requestDto.getName())
                .build();
        Extension saveExtension = extensionRepository.save(extension);
        return saveExtension.getId();
    }

    @Override
    @Transactional
    public void deleteExtension(String extensionName) {
        extensionRepository.deleteByName(extensionName);
    }

    @Override
    @Transactional(readOnly = true)
    public ExtensionListResponseDto findAllExtension() {
        List<Extension> allExtension = extensionRepository.findAll();
        List<ExtensionResponseDto> fixExtensionList = new ArrayList<>();
        List<ExtensionResponseDto> extensionList = new ArrayList<>();

        allExtension.forEach(e -> {
            ExtensionResponseDto extensionResponseDto = new ExtensionResponseDto(e.getId(), e.getName());
            if (isFixExtension(e.getName())) {
                fixExtensionList.add(extensionResponseDto);
            } else {
                extensionList.add(extensionResponseDto);
            }
        });

        return ExtensionListResponseDto.of(fixExtensionList, extensionList);
    }

    private boolean isFixExtension(String name) {
        if (name.equals("bat") || name.equals("cmd") || name.equals("com") ||
                name.equals("cpl") || name.equals("exe") || name.equals("scr") || name.equals("js")) {
            return true;
        }
        return false;
    }
}
