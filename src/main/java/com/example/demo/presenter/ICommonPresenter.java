package com.example.demo.presenter;

import com.example.demo.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ICommonPresenter {
    <T> ResponseEntity<ApiResponse<T>> getSuccessResponse(T response, HttpStatus status, String... messages);
}

