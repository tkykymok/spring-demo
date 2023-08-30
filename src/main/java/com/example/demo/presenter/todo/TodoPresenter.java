package com.example.demo.presenter.todo;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;

@Service
public class TodoPresenter implements ITodoPresenter {

    @Override
    public ResponseEntity<ApiResponse<GetTodoRes>> getTodoResponse(GetTodoRes response) {
        if (response != null) {
            return new ResponseEntity<>(new ApiResponse<>(response), HttpStatus.OK);
        } else {
            List<String> messages = Collections.singletonList("Todo not found.");
            return new ResponseEntity<>(new ApiResponse<>(null, messages), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<GetAllTodosRes>> getAllTodoResponse(GetAllTodosRes response) {
        if (response != null) {
            return new ResponseEntity<>(new ApiResponse<>(response), HttpStatus.OK);
        } else {
            List<String> messages = Collections.singletonList("Todo not found.");
            return new ResponseEntity<>(new ApiResponse<>(null, messages), HttpStatus.NOT_FOUND);
        }
    }

}
