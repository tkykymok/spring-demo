package com.example.demo.presenter;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.response.ApiResponse;

public abstract class BasePresenter {

    protected <T> ResponseEntity<ApiResponse<T>> createResponse(T response) {
        if (response != null) {
            return new ResponseEntity<>(new ApiResponse<>(response), HttpStatus.OK);
        } else {
            List<String> messages = Collections.singletonList("Data not found.");
            return new ResponseEntity<>(new ApiResponse<>(null, messages), HttpStatus.NOT_FOUND);
        }
    }

}
