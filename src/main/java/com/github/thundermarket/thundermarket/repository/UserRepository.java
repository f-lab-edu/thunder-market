package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    List<String> findEmailByIdIn(List<Long> userIds);
}