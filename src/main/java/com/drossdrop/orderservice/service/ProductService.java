package com.drossdrop.orderservice.service;

import com.drossdrop.orderservice.model.Product;
import com.drossdrop.orderservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(Product product) {
        Product newProduct = Product.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

        productRepository.save(newProduct);
    }
}
