package com.example.springbootplsql.repository;

import com.example.springbootplsql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
