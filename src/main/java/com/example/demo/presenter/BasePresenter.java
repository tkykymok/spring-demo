package com.example.demo.presenter;

import com.example.demo.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

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
