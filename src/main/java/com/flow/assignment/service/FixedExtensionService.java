package com.flow.assignment.service;

import com.flow.assignment.dto.request.CreateFixedExtensionRequest;
import com.flow.assignment.dto.response.FixedExtensionResponse;
import com.flow.assignment.entity.FixedExtension;
import com.flow.assignment.entity.enumerated.FixedExtensionType;
import com.flow.assignment.repository.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FixedExtensionService {

    private final FixedExtensionRepository repository;

    @Transactional
    public FixedExtensionResponse update(CreateFixedExtensionRequest request) {
        FixedExtension fixedExtension = repository.existsByName(request.getName())
                ? updateExistingExtension(request)
                : createNewExtension(request);

        return FixedExtensionResponse.fromEntity(fixedExtension);
    }

    private FixedExtension createNewExtension(CreateFixedExtensionRequest request) {
        FixedExtension newExtension = FixedExtension.builder()
                .name(request.getName())
                .isChecked(request.getIsChecked())
                .build();
        return repository.save(newExtension);
    }

    private FixedExtension updateExistingExtension(CreateFixedExtensionRequest request) {
        FixedExtension existing = repository.findByNameOrElseThrow(request.getName());
        existing.updateIsChecked(request.getIsChecked());
        return existing;
    }

    @Transactional(readOnly = true)
    public List<FixedExtensionResponse> get() {
        Map<String, FixedExtension> extensionMap = repository.findAll().stream()
                .collect(Collectors.toMap(
                        FixedExtension::getName,
                        fixedExtension -> fixedExtension
                ));

        return FixedExtensionType.getNames()
                .stream()
                .map(name -> mapToResponse(name, extensionMap))
                .collect(toList());
    }

    private FixedExtensionResponse mapToResponse(String name, Map<String, FixedExtension> extensionMap) {
        return extensionMap.containsKey(name)
                ? FixedExtensionResponse.fromEntity(extensionMap.get(name))
                : FixedExtensionResponse.from(name, false);
    }
}
