package ru.bmstu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.entities.user.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
