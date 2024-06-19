package ru.bmstu.services.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.dto.user.UserDTO;
import ru.bmstu.entities.user.User;
import ru.bmstu.repository.UserRepository;
import ru.bmstu.services.user.UserService;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User create(UserDTO userDTO) {
        return userRepository.save(User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build());
    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public User getUser(Integer id) {
        if(userRepository.existsById(id)) {
            return userRepository.getReferenceById(id);
        }
        return null;
    }
    @Override
    public boolean deleteUser(Integer id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateUser(Integer id, String login) {
        if(userRepository.existsById(id)) {
            userRepository.getReferenceById(id).setName(login);
            userRepository.save(userRepository.getReferenceById(id));
            return true;
        }
        return false;
    }
}
