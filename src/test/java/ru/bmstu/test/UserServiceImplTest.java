package ru.bmstu.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bmstu.repository.UserRepository;
import ru.bmstu.services.user.impl.UserServiceImpl;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserServiceImpl userService;

    @Test
    void usersExists() {
        Assertions.assertEquals(userRepo.findAll(), userService.getAll());
    }
}
