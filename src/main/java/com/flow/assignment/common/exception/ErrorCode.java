package com.flow.assignment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_TYPE_VALUE(400, "C001", "Invalid type value"),
    INVALID_INPUT_VALUE(400, "C003", "Invalid input value"),
    METHOD_NOT_ALLOWED(405, "C004", "Method not allowed"),
    ACCESS_DENIED(403, "C005", "Access denied"),
    INTERNAL_SERVER_ERROR(500, "C006", "Internal server error"),
    ENTITY_NOT_FOUND(404, "C007", "Not Found"),


    // CustomException
    EXCEED_TOTAL_CUSTOM_COUNT(400, "CE001", "Exceed total custom extension count"),
    FORBIDDEN_FIXED_EXTENSION(400, "CE002", "Forbidden fixed extension");


    private final int status;
    private final String code;
    private final String message;
}
