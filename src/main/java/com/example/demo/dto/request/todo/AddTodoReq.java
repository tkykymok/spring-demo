package com.example.demo.dto.request.todo;

import lombok.Data;

@Data
public class AddTodoReq {
    private String title;
    private String description;
    private Long userId;
}
