package com.example.demo.presenter;

import com.example.demo.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommonPresenter extends BasePresenter implements ICommonPresenter {
    @Override
    public <T> ResponseEntity<ApiResponse<T>> getSuccessResponse(T response, HttpStatus status,  String ...messages) {
        return createResponse(response, status, messages);
    }
}
