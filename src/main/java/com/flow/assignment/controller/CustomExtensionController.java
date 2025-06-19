package com.flow.assignment.controller;

import com.flow.assignment.dto.request.CreateCustomExtensionRequest;
import com.flow.assignment.dto.response.BaseResponse;
import com.flow.assignment.dto.response.CustomExtensionResponse;
import com.flow.assignment.service.CustomExtensionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/extension/custom")
public class CustomExtensionController {

    private final CustomExtensionService service;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> create(
            @RequestBody @Valid CreateCustomExtensionRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.from(service.create(request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse<Void>> delete(
            @PathVariable(name = "id") Long id
    ) {
        service.delete(id);
        return ResponseEntity.ok(BaseResponse.from(null));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CustomExtensionResponse>>> get(
    ) {
        return ResponseEntity.ok(BaseResponse.from(service.get()));
    }
}
