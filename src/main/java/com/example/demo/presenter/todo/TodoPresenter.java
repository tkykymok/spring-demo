package com.example.demo.presenter.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;
import com.example.demo.presenter.BasePresenter;

@Service
public class TodoPresenter extends BasePresenter implements ITodoPresenter {

    @Override
    public ResponseEntity<ApiResponse<GetTodoRes>> getTodoResponse(GetTodoRes response) {
        return createResponse(response);
    }

    @Override
    public ResponseEntity<ApiResponse<GetAllTodosRes>> getAllTodoResponse(GetAllTodosRes response) {
        return createResponse(response);
    }
}
