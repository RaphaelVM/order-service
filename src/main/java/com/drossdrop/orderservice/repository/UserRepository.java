package com.drossdrop.orderservice.repository;

import com.drossdrop.orderservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
