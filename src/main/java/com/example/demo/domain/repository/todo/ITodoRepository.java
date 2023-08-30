package com.example.demo.domain.repository.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.entity.Todo;

public interface ITodoRepository extends JpaRepository<Todo, Long> {
    // 1. 特定の属性に基づいてエンティティを検索
    List<Todo> findByTitle(String title);

    // 2. 特定の属性に基づいてエンティティを検索 (頭文字マッチ)
    List<Todo> findByTitleStartingWith(String prefix);

    // 3. 特定の属性に基づいてエンティティを検索 (部分一致)
    List<Todo> findByTitleContaining(String part);

    // 4. 特定の属性でエンティティをカウント
    long countByTitle(String title);

    // 5. 属性の値に基づいてエンティティを削除
    long deleteByTitle(String title);

    // 6. 2つ以上の属性に基づいてエンティティを検索
    List<Todo> findByTitleAndDescription(String name, String status);

    // 7. 特定の属性に基づくエンティティの存在チェック
    boolean existsByTitle(String name);

    // 8. @Queryアノテーションを使用してカスタムクエリを定義
    @Query("SELECT t FROM Todo t WHERE t.title = ?1 AND t.userId = ?2")
    List<Todo> findByTitleAndUserIdCustom(String name, Long userId);

    
}
