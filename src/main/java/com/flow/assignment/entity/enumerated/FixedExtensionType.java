package com.flow.assignment.entity.enumerated;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum FixedExtensionType {
    BAT("bat"),
    CMD("cmd"),
    COM("com"),
    CPL("cpl"),
    EXE("exe"),
    SCR("scr"),
    JS("js");

    FixedExtensionType(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }

    public static List<String> getNames() {
        return Arrays.stream(FixedExtensionType.values())
                .map(FixedExtensionType::getName)
                .collect(toList());
    }
}
