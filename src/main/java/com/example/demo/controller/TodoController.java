package com.example.demo.controller;

import com.example.demo.dto.request.todo.AddTodoReq;
import com.example.demo.dto.response.todo.GetTodosWithRelatedRes;
import com.example.demo.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.usecase.todo.ITodoUseCase;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;
import com.example.demo.presenter.todo.ITodoPresenter;

import java.io.IOException;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    MessageHelper messageHelper;

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

    @PostMapping("/addTodo")
    public ResponseEntity<?> addTodo(@RequestBody AddTodoReq request) {
        request.setUserId(1L);
        todoUseCase.createTodo(request);
        return todoPresenter.getSuccessResponse(messageHelper.getMessage("completed", "登録"));
    }

}