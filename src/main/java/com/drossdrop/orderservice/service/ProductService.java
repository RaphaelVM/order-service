package com.drossdrop.orderservice.service;

import com.drossdrop.orderservice.model.Product;
import com.drossdrop.orderservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public void createProduct(Product product) {
        Product newProduct = Product.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

        productRepository.save(newProduct);
        LOGGER.info(String.format("Product created...-> %s", newProduct.toString()));
    }
}
