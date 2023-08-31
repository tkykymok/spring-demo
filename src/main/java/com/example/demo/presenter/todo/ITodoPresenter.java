package com.example.demo.presenter.todo;

import com.example.demo.dto.response.todo.GetTodosWithRelatedRes;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;

public interface ITodoPresenter extends ICommonPresenter{
    ResponseEntity<ApiResponse<GetTodoRes>> getTodoResponse(GetTodoRes response);
    
    ResponseEntity<ApiResponse<GetAllTodosRes>> getAllTodoResponse(GetAllTodosRes response);

    ResponseEntity<ApiResponse<GetTodosWithRelatedRes>> getTodosWithRelatedResponse(GetTodosWithRelatedRes response);

}

