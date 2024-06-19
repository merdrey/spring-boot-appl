package ru.bmstu.controller.user;

import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.dto.user.UserDTO;
import ru.bmstu.entities.user.User;
import ru.bmstu.services.user.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getStatus")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("Works!", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/createUserByLogin")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAll() {

        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id) != null
                ? new ResponseEntity<>(userService.getUser(id), HttpStatus.OK)
                : new ResponseEntity<>("User with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
        return userService.deleteUser(id)
                ? new ResponseEntity<>("User was deleted", HttpStatus.OK)
                : new ResponseEntity<>("User with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/updateUser/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Integer id, @RequestParam(value = "name") String name) {
        return userService.updateUser(id, name)
                ? new ResponseEntity<>(userService.getUser(id), HttpStatus.OK)
                : new ResponseEntity<>("User with id " + id + " does not exist",HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getUser")
    public ResponseEntity<?> getByLogin(@RequestParam(value = "name") String name) {
        for(User user : userService.getAll()) {
            if(user.getName().equals(name)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No user with login " + name, HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteUser")
    public ResponseEntity<?> deleteByLogin(@RequestParam(value = "name") String name) {
        for(User user : userService.getAll()) {
            if(user.getName().equals(name)) {
                userService.deleteUser(user.getId());
                return new ResponseEntity<>("User " + name + " deleted", HttpStatus.OK);

            }
        }
        return new ResponseEntity<>("No user with login " + name, HttpStatus.NOT_FOUND);
    }
}
