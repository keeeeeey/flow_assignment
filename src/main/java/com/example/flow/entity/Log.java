package com.example.flow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Log extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    private String name;

    private String method;

    public static Log of(String name, String method) {
        return Log.builder()
                .name(name)
                .method(method)
                .build();
    }
}
