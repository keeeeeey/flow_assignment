package com.flow.assignment.controller;

import com.flow.assignment.service.FixedExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/extension/fixed")
public class FixedExtensionController {

    private final FixedExtensionService service;

}
