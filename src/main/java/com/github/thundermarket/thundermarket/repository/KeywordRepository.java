package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    List<Keyword> findAllByUserId(Long userId);
}
