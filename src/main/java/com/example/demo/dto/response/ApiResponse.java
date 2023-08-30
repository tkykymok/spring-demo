package com.example.demo.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private T data;
    private List<String> messages;

    public ApiResponse(T data) {
        this.data = data;
        this.messages = new ArrayList<>();
    }

    public ApiResponse(T data, List<String> messages) {
        this.data = data;
        this.messages = messages;
    }

}