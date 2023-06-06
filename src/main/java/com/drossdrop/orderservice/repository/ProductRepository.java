package com.drossdrop.orderservice.repository;

import com.drossdrop.orderservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
