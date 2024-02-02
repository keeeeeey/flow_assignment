package com.example.flow.controller;

import com.example.flow.common.exception.ApiException;
import com.example.flow.common.exception.ApiExceptionEntity;
import com.example.flow.common.exception.ExceptionEnum;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * API에서 발생한 에러를 핸들링합니다.
 */
@RestControllerAdvice
public class ExceptionAdviceController {

    /**
     * API Exception이 발생했을 경우, 예외를 client로 보냅니다.
     * @param e
     * @return
     */
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> apiExceptionHandler(final ApiException e) {

        return new ResponseEntity<>(
                new ApiExceptionEntity(
                        e.getError().getCode(),
                        e.getError().getMessage()
                ),
                e.getError().getStatus()
        );
    }

    /**
     * RuntimeException, Exception 발생시 내부서버에러(500)를 표시합니다.
     * @return
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ApiExceptionEntity> runTimeExceptionHandler() {
        return apiExceptionHandler(new ApiException(ExceptionEnum.INTERNAL_SERVER_ERROR));
    }

    /**
     * 지원하지 않는 API요청이 왔을 경우 기본 처리입니다.
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiExceptionEntity> methodNotSupportExceptionHandler() {
        return apiExceptionHandler(new ApiException(ExceptionEnum.API_METHOD_NOT_ALLOWED_EXCEPTION));
    }

    /**
     * 파라미터 검증에서 실패했을 경우 기본 처리입니다.
     * @return
     */
    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<ApiExceptionEntity> parameterException() {
        return apiExceptionHandler(new ApiException(ExceptionEnum.API_PARAMETER_EXCEPTION));
    }

    /**
     * 파라미터와 바디 데이터가 잘못된 경우 기본처리입니다.
     * @return
     */
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ApiExceptionEntity> badRequestException() {
        return apiExceptionHandler(new ApiException(ExceptionEnum.API_PARAMETER_EXCEPTION));
    }
}