package com.example.flow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExtensionRequestDto {

    @NotBlank(message = "차단 확장자를 입력해주세요.")
    @Size(min = 1, max = 20, message = "최대 글자 수는 20자 입니다.")
    private String name;
}
