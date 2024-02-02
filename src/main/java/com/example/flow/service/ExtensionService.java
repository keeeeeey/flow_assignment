package com.example.flow.service;

import com.example.flow.dto.request.ExtensionRequestDto;
import com.example.flow.dto.response.ExtensionListResponseDto;

public interface ExtensionService {
    Long createExtension(ExtensionRequestDto requestDto);

    void deleteExtension(String extensionName);

    ExtensionListResponseDto findAllExtension();
}
