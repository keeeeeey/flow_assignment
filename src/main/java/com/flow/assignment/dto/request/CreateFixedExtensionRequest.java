package com.flow.assignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFixedExtensionRequest {
    @Size(max = 20, message = "확장자는 최대 20자까지 입력할 수 있습니다.")
    @NotBlank(message = "확장자를 입력해주세요.")
    private String name;

    @Builder.Default
    private Boolean isChecked = false;
}
