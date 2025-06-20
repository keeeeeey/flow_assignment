package com.flow.assignment.dto.response;

import com.flow.assignment.entity.FixedExtension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedExtensionResponse {
    private Long id;
    private String name;
    private Boolean isChecked;

    public static FixedExtensionResponse fromEntity(FixedExtension fixedExtension) {
        return builder()
                .id(fixedExtension.getId())
                .name(fixedExtension.getName())
                .isChecked(fixedExtension.getIsChecked())
                .build();
    }

    public static FixedExtensionResponse from(String name, Boolean isChecked) {
        return builder()
                .name(name)
                .isChecked(isChecked)
                .build();
    }
}
