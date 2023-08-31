package com.example.demo.presenter;

import com.example.demo.dto.response.ApiErrorResponse;
import com.example.demo.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public abstract class BasePresenter {

    protected <T> ResponseEntity<ApiResponse<T>> createResponse(T response, HttpStatus status, String... messages) {
        List<String> messageList = Arrays.asList(messages);
        return new ResponseEntity<>(new ApiResponse<>(response, messageList), status);
    }

    protected ResponseEntity<ApiErrorResponse> createErrorResponse(HttpStatus status, String detail, String... messages) {
        List<String> messageList = Arrays.asList(messages);
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.name(),
                detail,
                messageList
        );
        return new ResponseEntity<>(errorResponse, status);
    }

}
