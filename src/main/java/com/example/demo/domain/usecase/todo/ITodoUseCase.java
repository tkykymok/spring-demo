package com.example.demo.domain.usecase.todo;

import java.io.IOException;

import com.example.demo.dto.request.todo.AddTodoReq;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;
import com.example.demo.dto.response.todo.GetTodosWithRelatedRes;

public interface ITodoUseCase {
    GetTodoRes fetchTodo(Long todoId);
    
    GetAllTodosRes fetchAllTodos();

    GetTodosWithRelatedRes fetchTodosWithRelated(Long userId) throws IOException;

    void createTodo(AddTodoReq request);
}
