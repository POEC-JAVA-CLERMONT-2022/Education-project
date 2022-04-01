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
        User user = this.userService.createUser("Wendy", "P", LocalDate.parse("1990-03-03"), "jkhkhj", "xxxxx@gmail.com", "jkljk", "sin status");
        Assertions.assertNotNull(user);
    }

    @Test
    public void updateUserTest() {
        //Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status)
        //User user = this.userService.updateUser("Wendy", "P", LocalDate.parse("1990-03-03"), "jkhkhj", "xxxxx@gmail.com", "jkljk", "sin status");
        //Assertions.assertNotNull(user);
    }
}

