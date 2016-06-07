package com.alex.study.springsecurity.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.study.springsecurity.domain.security.db.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUuid(String uuid);
}
