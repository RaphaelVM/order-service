package com.drossdrop.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String email;

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', email='%s']",
                id, username, email);
    }
}
