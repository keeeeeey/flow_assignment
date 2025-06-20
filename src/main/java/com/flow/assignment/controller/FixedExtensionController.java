package com.flow.assignment.controller;

import com.flow.assignment.dto.request.CreateFixedExtensionRequest;
import com.flow.assignment.dto.response.BaseResponse;
import com.flow.assignment.dto.response.FixedExtensionResponse;
import com.flow.assignment.service.FixedExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/extension/fixed")
public class FixedExtensionController {

    private final FixedExtensionService service;

    @PatchMapping
    public ResponseEntity<BaseResponse<FixedExtensionResponse>> update(
            @RequestBody CreateFixedExtensionRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.from(service.update(request)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<FixedExtensionResponse>>> get(
    ) {
        return ResponseEntity.ok(BaseResponse.from(service.get()));
    }
}
