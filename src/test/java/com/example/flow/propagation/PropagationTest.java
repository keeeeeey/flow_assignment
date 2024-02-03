package com.example.flow.propagation;

import com.example.flow.dto.request.ExtensionRequestDto;
import com.example.flow.repository.ExtensionRepository;
import com.example.flow.repository.LogRepository;
import com.example.flow.service.ExtensionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PropagationTest {

    @Autowired
    ExtensionService extensionService;

    @Autowired
    ExtensionRepository extensionRepository;

    @Autowired
    LogRepository logRepository;

    @Test
    void recoverException_success() {
        // given
        ExtensionRequestDto requestDto = new ExtensionRequestDto("bat");

        // when
        extensionService.createExtension(requestDto);

        // then: extension 저장, log 롤백
        assertTrue(extensionRepository.findByName(requestDto.getName()).isPresent());
        assertTrue(logRepository.findByName(requestDto.getName()).isEmpty());
    }
}
