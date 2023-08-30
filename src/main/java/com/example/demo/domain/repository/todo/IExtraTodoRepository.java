package com.example.demo.domain.repository.todo;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.domain.entity.extra.ExtraTodo;


@NoRepositoryBean
public interface IExtraTodoRepository {
    List<ExtraTodo> findTodosWithRelated(Long userId) throws IOException;
}
