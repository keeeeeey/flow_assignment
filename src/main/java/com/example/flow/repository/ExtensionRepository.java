package com.example.flow.repository;

import com.example.flow.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    void deleteByName(String extensionName);
}
