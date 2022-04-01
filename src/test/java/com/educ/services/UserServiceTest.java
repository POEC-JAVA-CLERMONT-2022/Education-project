package com.educ.services;

import com.educ.data.UserRepository;
import com.educ.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void createUserTest() {
        //LocalDate birthd = LocalDate.parse("1990-03-03");
        User user = this.userService.createUser("Wendy", "P", LocalDate.parse("1990-03-03"), "jkhkhj", "xxxxx@gmail.com", "jkljk", "sin status");
        Assertions.assertNotNull(user);
    }
}

