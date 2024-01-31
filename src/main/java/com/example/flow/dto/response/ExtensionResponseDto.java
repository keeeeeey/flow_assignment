package com.example.flow.dto.response;

import com.example.flow.entity.Extension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtensionResponseDto {
    private String name;

    public static ExtensionResponseDto from(Extension extension) {
        return ExtensionResponseDto.builder()
                .name(extension.getName())
                .build();
    }
}
