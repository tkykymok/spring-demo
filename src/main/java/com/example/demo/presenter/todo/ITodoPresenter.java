package com.example.demo.presenter.todo;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;

public interface ITodoPresenter {
    ResponseEntity<ApiResponse<GetTodoRes>> getTodoResponse(GetTodoRes response);
    
    ResponseEntity<ApiResponse<GetAllTodosRes>> getAllTodoResponse(GetAllTodosRes response);
}

