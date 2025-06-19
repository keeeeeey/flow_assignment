package com.flow.assignment.dto.response;

import com.flow.assignment.entity.CustomExtension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomExtensionResponse {
    private Long id;
    private String name;

    public static CustomExtensionResponse fromEntity(CustomExtension customExtension) {
        return builder()
                .id(customExtension.getId())
                .name(customExtension.getName())
                .build();
    }
}
