package com.example.demo.domain.entity.extra;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import lombok.Data;

@Data
@Entity
@SqlResultSetMapping(name = "ExtraTodoMapping", classes = @ConstructorResult(targetClass = ExtraTodo.class, columns = {
        @ColumnResult(name = "todo_id", type = Long.class),
        @ColumnResult(name = "todo_title", type = String.class),
        @ColumnResult(name = "user_id", type = Long.class),
        @ColumnResult(name = "user_name", type = String.class),
        @ColumnResult(name = "city_id", type = Long.class),
        @ColumnResult(name = "city_name", type = String.class)
}))
public class ExtraTodo {
    @Id
    private Long todoId;
    private String todoTitle;
    private Long userId;
    private String userName;
    private Long cityId;
    private String cityName;
}
