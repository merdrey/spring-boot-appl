package ru.bmstu.services.user;

import ru.bmstu.dto.user.UserDTO;
import ru.bmstu.entities.user.User;

import java.util.List;

public interface UserService {
    User create(UserDTO userDTO);
    List<User> getAll();
    User getUser(Integer id);
    boolean deleteUser(Integer id);
    boolean updateUser(Integer id, String login);
}
