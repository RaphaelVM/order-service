package com.drossdrop.orderservice.service;

import com.drossdrop.orderservice.model.User;
import com.drossdrop.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public void createUser(User user) {
        User newUser = User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

        userRepository.save(newUser);
        LOGGER.info(String.format("User created...-> %s", newUser.toString()));
    }
}
