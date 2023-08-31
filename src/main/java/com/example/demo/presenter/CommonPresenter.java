package com.example.demo.presenter;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetTodoRes;
import com.example.demo.dto.response.todo.GetTodosWithRelatedRes;
import com.example.demo.presenter.BasePresenter;
import com.example.demo.presenter.todo.ICommonPresenter;
import com.example.demo.presenter.todo.ITodoPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommonPresenter extends BasePresenter implements ICommonPresenter {
    @Override
    public ResponseEntity<ApiResponse<Object>> getSuccessResponse(String ...messages) {
        List<String> messageList = Arrays.asList(messages);
        return new ResponseEntity<>(new ApiResponse<>(null, messageList), HttpStatus.OK);
    }
}
