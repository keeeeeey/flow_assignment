package com.example.flow.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "내부 문제로 다음번에 다시 시도해주세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0002", "내부 문제로 다음번에 다시 시도해주세요."),
    API_NOT_EXIST_EXCEPTION(HttpStatus.NOT_FOUND, "E0003", "존재하지 않는 API 입니다."),
    API_METHOD_NOT_ALLOWED_EXCEPTION(HttpStatus.METHOD_NOT_ALLOWED, "E0004", "지원하지 않는 Method 입니다."),
    API_PARAMETER_EXCEPTION(HttpStatus.BAD_REQUEST, "E0005", "파라미터 타입과 값을 확인하세요."),
    EXTENSION_NOT_EXIST_EXCEPTION(HttpStatus.NOT_FOUND, "E0006", "존재하지 않은 차단 확장자입니다."),
    EXTENSION_DUPLICATE_EXCEPTION(HttpStatus.BAD_REQUEST, "E0007", "이미 차단된 확장자입니다."),
    MAX_EXTENSION_COUNT_OVER_EXCEPTION(HttpStatus.BAD_REQUEST, "E0008", "최대 저장 가능한 갯수를 초과했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}