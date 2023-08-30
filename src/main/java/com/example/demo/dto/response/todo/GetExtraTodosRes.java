package com.example.demo.dto.response.todo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetExtraTodosRes {
    private List<ExtraTodoRes>  todos; 

    @Data
    public class ExtraTodoRes {
        private Long todoId;
        private String todoTask;
        private Long userId;
        private String userName;
        private Long cityId;
        private String cityName;
    }

}
