package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.dto.KeywordResponse;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    List<Keyword> findAllByUserId(Long userId);

    @Query("SELECT user_id FROM keywords WHERE :title LIKE CONCAT('%', keyword, '%')")
    List<Long> findUserIdsWithMatchingKeyword(@Param("title") String title);
}
