package com.example.demo.dto.response.todo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class GetTodosWithRelatedRes {
    private List<TodoWithRelated>  todos;

    @Data
    @NoArgsConstructor
    public static class TodoWithRelated {
        private Long todoId;
        private String todoTitle;
        private Long userId;
        private String userName;
        private Long cityId;
        private String cityName;
    }

}
