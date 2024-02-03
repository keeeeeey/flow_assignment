package com.example.flow.service;

import com.example.flow.entity.Log;
import com.example.flow.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logSave(String extensionName, String method) {
        Log logData = Log.of(extensionName, method);
        logRepository.save(logData);
    }
}
