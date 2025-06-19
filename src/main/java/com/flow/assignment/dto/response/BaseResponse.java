package com.flow.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> {
    private T data;

    public static <T> BaseResponse<T> from(T data) {
        return BaseResponse.<T>builder()
                .data(data)
                .build();
    }
}
