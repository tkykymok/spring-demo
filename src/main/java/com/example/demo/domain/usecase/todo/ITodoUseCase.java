package com.example.demo.domain.usecase.todo;

import java.io.IOException;

import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetExtraTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;

public interface ITodoUseCase {
    GetTodoRes fetchTodo(Long todoId);
    
    GetAllTodosRes fetchAllTodos();
    
    GetExtraTodosRes fetchTodoWithRelated(Long userId) throws IOException;
}
