package com.flow.assignment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "유효하지 않은 입력값 입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "Method not allowed"),
    INTERNAL_SERVER_ERROR(500, "C003", "Internal server error"),
    ENTITY_NOT_FOUND(404, "C004", "Entity not found"),


    // CustomException
    EXCEED_TOTAL_CUSTOM_COUNT(400, "CE001", "추가 가능한 커스텀 확장자 개수를 초과했습니다."),
    FORBIDDEN_FIXED_EXTENSION(400, "CE002", "고정 확장자는 추가할 수 없습니다."),
    ALREADY_CREATED_EXTENSION(400, "CE003", "이미 추가된 커스텀 확장자입니다."),
    ALREADY_DELETED_EXTENSION(400, "CE004", "이미 삭제된 커스텀 확장자입니다.");


    private final int status;
    private final String code;
    private final String message;
}
