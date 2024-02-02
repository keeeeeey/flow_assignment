package com.example.flow.entity;

import com.example.flow.dto.request.ExtensionRequestDto;
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

    public static Extension of(ExtensionRequestDto requestDto, boolean isFixExtension) {
        return Extension.builder()
                .name(requestDto.getName())
                .isFixExtension(isFixExtension)
                .build();
    }
}
