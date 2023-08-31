package com.example.demo.domain.repository.todo;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.extra.ExtraTodo;
import com.example.demo.util.SQLLoader;


@Repository
public class ExtraTodoRepository implements IExtraTodoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SQLLoader sqlLoader;

    @Override
    public List<ExtraTodo> findTodosWithRelated(Long userId) throws IOException {
        String sql = sqlLoader.loadSQL("todo/findTodosWithRelated.sql");
        Query query = entityManager.createNativeQuery(sql, ExtraTodo.class);
        query.setParameter("userIdParam", userId);
        @SuppressWarnings("unchecked")
        List<ExtraTodo> result = query.getResultList();
        return result;
    }

}
