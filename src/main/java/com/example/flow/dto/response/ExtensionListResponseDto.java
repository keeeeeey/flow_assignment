package com.example.flow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtensionListResponseDto {
    private List<ExtensionResponseDto> fixExtensionList;
    private List<ExtensionResponseDto> extensionList;

    public static ExtensionListResponseDto of(List<ExtensionResponseDto> fixExtensionList, List<ExtensionResponseDto> extensionList) {
        return ExtensionListResponseDto.builder()
                .fixExtensionList(fixExtensionList)
                .extensionList(extensionList)
                .build();
    }
}
