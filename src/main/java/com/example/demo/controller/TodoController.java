package com.example.demo.controller;

import com.example.demo.dto.request.todo.AddTodoReq;
import com.example.demo.dto.response.todo.GetTodosWithRelatedRes;
import com.example.demo.presenter.ICommonPresenter;
import com.example.demo.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.usecase.todo.ITodoUseCase;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;


import java.io.IOException;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    MessageHelper messageHelper;

    @Autowired
    private ITodoUseCase todoUseCase;

    @Autowired
    private ICommonPresenter presenter;

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodo(@PathVariable Long todoId) {
        GetTodoRes response = todoUseCase.fetchTodo(todoId);
        return presenter.getSuccessResponse(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTodo() {
        GetAllTodosRes response = todoUseCase.fetchAllTodos();
        return presenter.getSuccessResponse(response, HttpStatus.OK);
    }

    @GetMapping("/extra-sample/{userId}")
    public ResponseEntity<?> getTodosWithRelated(@PathVariable Long userId) throws IOException {
        GetTodosWithRelatedRes response = todoUseCase.fetchTodosWithRelated(userId);
        return presenter.getSuccessResponse(response, HttpStatus.OK);
    }

    @PostMapping("/addTodo")
    public ResponseEntity<?> addTodo(@RequestBody AddTodoReq request) {
        request.setUserId(1L);
        todoUseCase.createTodo(request);
        return presenter.getSuccessResponse(null, HttpStatus.OK, messageHelper.getMessage("completed", "登録"));
    }

}