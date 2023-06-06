package com.drossdrop.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String productId;
    private String userId;
    private Integer quantity;
}
