package com.example.flow.service;

import com.example.flow.dto.request.ExtensionRequestDto;
import com.example.flow.dto.response.ExtensionResponseDto;
import com.example.flow.entity.Extension;
import com.example.flow.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ExtensionResponseDto> findAllExtension() {
        List<Extension> allExtension = extensionRepository.findAll();
        return allExtension.stream().map(ExtensionResponseDto::from).toList();
    }
}
