package com.example.flow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtensionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extension_history_id")
    private Long id;

    private String name;

    private int saveCount;

    public static ExtensionHistory of(String name, int saveCount) {
        return ExtensionHistory.builder()
                .name(name)
                .saveCount(saveCount)
                .build();
    }

    public void plusSaveCount() {
        this.saveCount += 1;
    }
}
