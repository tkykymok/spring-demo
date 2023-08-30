package com.example.demo.dto.response.todo;

import lombok.Data;

@Data
public class GetTodoRes {
    private Long id;
    private String title;
    private String description;
    private Long userId;
}
