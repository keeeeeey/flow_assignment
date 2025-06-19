package com.flow.assignment.service;

import com.flow.assignment.repository.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FixedExtensionService {

    private final FixedExtensionRepository repository;
}
