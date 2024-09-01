package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    List<User> findEmailByIdIn(List<Long> userIds);
}