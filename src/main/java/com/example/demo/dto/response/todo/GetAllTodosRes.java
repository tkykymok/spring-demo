package com.example.demo.dto.response.todo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllTodosRes {
    List<GetTodoRes> todos;
}
