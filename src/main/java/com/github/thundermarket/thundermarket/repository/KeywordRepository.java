package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Keyword;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    List<Keyword> findAllByUserId(Long userId);

    @Query("SELECT k.userId FROM Keyword k WHERE :title LIKE CONCAT('%', k.keyword, '%')")
    List<Long> findUserIdsWithMatchingKeyword(@Param("title") String title);
}
