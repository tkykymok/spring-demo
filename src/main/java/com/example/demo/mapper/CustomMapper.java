package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CustomMapper extends BaseMapper {

    // Request -> Entity
    public <Q, E> E fromRequest(Q request, Class<E> entityClass) {
        return map(request, entityClass);
    }

    // Entity -> Response
    public <E, S> S fromEntity(E entity, Class<S> responseClass) {
        return map(entity, responseClass);
    }

    // List Mapping
    public <Q, E> List<E> fromRequestList(List<Q> requestList, Class<E> entityClass) {
        return mapList(requestList, entityClass);
    }

    // List Mapping
    public <E, S> List<S> fromEntityList(List<E> entityList, Class<S> responseClass) {
        return mapList(entityList, responseClass);
    }

}

/** 使用例
TodoMapper todoMapper = new TodoMapper();

// Request -> Entity
Todo todoFromGetAllReq = todoMapper.fromRequest(getAllTodoReqInstance, Todo.class);
Todo todoFromGetReq = todoMapper.fromRequest(getTodoReqInstance, Todo.class);

// Entity -> Response
GetAllTodoRes getAllTodoResponse = todoMapper.fromEntity(todoInstance, GetAllTodoRes.class);
GetTodoRes getTodoResponse = todoMapper.fromEntity(todoInstance, GetTodoRes.class);


TodoMapper todoMapper = new TodoMapper();
List<TodoResponse> todoResponses = todoMapper.fromEntityList(todoEntityListInstance, TodoResponse.class);



 */
