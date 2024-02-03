package com.example.flow.repository;

import com.example.flow.entity.ExtensionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExtensionHistoryRepository extends JpaRepository<ExtensionHistory, Long> {
    Optional<ExtensionHistory> findByName(String name);

    @Query("select eh.name from ExtensionHistory eh where eh.saveCount >= 10")
    List<String> findAllOverTen();
}
