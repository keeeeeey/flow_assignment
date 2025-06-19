package com.flow.assignment.dto.request;

import com.flow.assignment.common.rule.Regex;
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
    @Size(max = 20)
    @NotBlank
    @Pattern(regexp = "^[^\\\\\\\\/:*?\\\"<>|\\\\.\\\\s\\\\x00-\\\\x1F]+$")
    private String name;
}
