package com.example.flow.repository;

import com.example.flow.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Optional<Extension> findByName(String name);
    void deleteByName(String extensionName);
}
