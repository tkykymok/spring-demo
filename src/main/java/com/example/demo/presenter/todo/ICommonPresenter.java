package com.example.demo.presenter.todo;

import com.example.demo.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ICommonPresenter {
    ResponseEntity<ApiResponse<Object>> getSuccessResponse(String ...messages);
}

