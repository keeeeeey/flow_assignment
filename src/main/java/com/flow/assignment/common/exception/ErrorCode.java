package com.flow.assignment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid input value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method not allowed"),
    INTERNAL_SERVER_ERROR(500, "C003", "Internal server error"),
    ENTITY_NOT_FOUND(404, "C004", "Entity not found"),
    ALREADY_CREATED_EXTENSION(400, "C005", "Already created extension"),
    ALREADY_DELETED_EXTENSION(400, "C006", "Already deleted extension"),


    // CustomException
    EXCEED_TOTAL_CUSTOM_COUNT(400, "CE001", "Exceed total custom extension count"),
    FORBIDDEN_FIXED_EXTENSION(400, "CE002", "Forbidden fixed extension");


    private final int status;
    private final String code;
    private final String message;
}
