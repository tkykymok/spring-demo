package com.example.demo.controller;

import com.example.demo.dto.response.todo.GetTodosWithRelatedRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.usecase.todo.ITodoUseCase;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;
import com.example.demo.presenter.todo.ITodoPresenter;

import java.io.IOException;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private ITodoUseCase todoUseCase;

    @Autowired
    private ITodoPresenter todoPresenter;

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodo(@PathVariable Long todoId) {
        GetTodoRes response = todoUseCase.fetchTodo(todoId);
        return todoPresenter.getTodoResponse(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTodo() {
        GetAllTodosRes response = todoUseCase.fetchAllTodos();
        return todoPresenter.getAllTodoResponse(response);
    }

    @GetMapping("/extra-sample/{userId}")
    public ResponseEntity<?> getTodosWithRelated(@PathVariable Long userId) throws IOException {
        GetTodosWithRelatedRes response = todoUseCase.fetchTodosWithRelated(userId);
        return todoPresenter.getTodosWithRelatedResponse(response);
    }

}