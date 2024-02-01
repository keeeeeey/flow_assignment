package com.example.flow.controller;

import com.example.flow.dto.BaseResponseDto;
import com.example.flow.dto.request.ExtensionRequestDto;
import com.example.flow.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService extensionService;

    @PostMapping()
    public ResponseEntity<BaseResponseDto<Long>> createExtension(@RequestBody ExtensionRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto<>(201, "success", extensionService.createExtension(requestDto)));
    }

    @DeleteMapping("/{extensionName}")
    public ResponseEntity<BaseResponseDto<Void>> deleteExtension(@PathVariable("extensionName") String extensionName) {
        extensionService.deleteExtension(extensionName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new BaseResponseDto<>(204, "success"));
    }
}
