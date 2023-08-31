package com.example.demo.exception;

import com.example.demo.dto.response.ApiErrorResponse;
import com.example.demo.presenter.CommonPresenter;
import com.example.demo.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    MessageHelper messageHelper;
    @Autowired
    CommonPresenter presenter;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return presenter.getErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), messageHelper.getMessage("system_error"));
    }

    // システムエラー
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception ex) {
        return presenter.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), messageHelper.getMessage("system_error"));
    }
}
