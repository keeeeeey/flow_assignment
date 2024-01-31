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

    private String name;
}
