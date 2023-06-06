package com.drossdrop.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    @Id
    private String id;
    private String productId;
    private String userId;
    private BigDecimal totalPrice;
    private String status;

    @Override
    public String toString() {
        return String.format(
                "Order[id=%s, productId='%s', userId='%s', totalPrice='%s', status='%s']",
                id, productId, userId, totalPrice, status);
    }
}
