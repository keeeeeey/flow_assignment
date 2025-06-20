package com.flow.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class FixedExtension extends BaseEntity{

    @Size(max = 20)
    @NotNull
    @Column(name = "name", unique = true, length = 20, nullable = false)
    private String name;

    @NotNull
    @Column(name = "is_checked", nullable = false)
    @Builder.Default
    private Boolean isChecked = false;

    public void updateIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}
