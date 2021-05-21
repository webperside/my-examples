package com.example.springbootmongodb.repository;

import com.example.springbootmongodb.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
