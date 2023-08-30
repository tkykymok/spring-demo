package com.example.demo.domain.usecase.todo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Todo;
import com.example.demo.domain.entity.extra.ExtraTodo;
import com.example.demo.domain.repository.todo.IExtraTodoRepository;
import com.example.demo.domain.repository.todo.ITodoRepository;
import com.example.demo.dto.response.todo.GetAllTodosRes;
import com.example.demo.dto.response.todo.GetExtraTodosRes;
import com.example.demo.dto.response.todo.GetExtraTodosRes.ExtraTodoRes;
import com.example.demo.dto.response.todo.GetTodoRes;
import com.example.demo.mapper.CustomMapper;

@Service
public class TodoUseCase implements ITodoUseCase {

    @Autowired
    private ITodoRepository todoRepository;
    @Autowired
    private IExtraTodoRepository extraTodoRepository;
    
    @Autowired
    private CustomMapper mapper;

    @Override
    public GetTodoRes fetchTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .map(todo -> mapper.fromEntity(todo, GetTodoRes.class))
                .orElse(null);
    }

    @Override
    public GetAllTodosRes fetchAllTodos() {
        List<Todo> todos = todoRepository.findAll();

        return new GetAllTodosRes(mapper.fromEntityList(todos, GetTodoRes.class));
    }

    @Override
    public GetExtraTodosRes fetchTodoWithRelated(Long userId) throws IOException {
        List<ExtraTodo> todos = extraTodoRepository.findTodosWithRelated(userId);

        return new GetExtraTodosRes(
                mapper.fromEntityList(todos, ExtraTodoRes.class));
    }

}
