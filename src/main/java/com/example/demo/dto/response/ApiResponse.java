package com.example.demo.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private List<String> messages;
}