package com.example.flow.service;

import com.example.flow.dto.request.ExtensionRequestDto;
import com.example.flow.dto.response.ExtensionResponseDto;

import java.util.List;

public interface ExtensionService {
    Long createExtension(ExtensionRequestDto requestDto);

    void deleteExtension(String extensionName);

    List<ExtensionResponseDto> findAllExtension();
}
