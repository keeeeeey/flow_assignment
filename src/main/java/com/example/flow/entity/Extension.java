package com.example.flow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extension_id")
    private Long id;

    @Column(unique = true, length = 20)
    private String name;

    private boolean isFixExtension;

    public static Extension of(String name, boolean isFixExtension) {
        return Extension.builder()
                .name(name)
                .isFixExtension(isFixExtension)
                .build();
    }
}
