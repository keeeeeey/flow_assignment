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
public class FixedExtensionResponse {
    private Long id;
    private String name;
    private Boolean isChecked;
}
