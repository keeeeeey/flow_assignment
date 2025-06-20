package com.flow.assignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomExtensionRequest {
    @Size(max = 20, message = "확장자는 최대 20자까지 입력할 수 있습니다.")
    @NotBlank(message = "확장자를 입력해주세요.")
    @Pattern(
            regexp = "^[^\\\\/:*?\"<>|\\.\\s\\x00-\\x1F]+$",
            message = "특수 문자, 공백, 마침표 등을 포함할 수 없습니다."
    )
    private String name;
}
