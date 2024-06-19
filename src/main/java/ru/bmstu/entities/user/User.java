package ru.bmstu.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bmstu.entities.player.Player;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Column(name = "u_id")
    private Integer id;
    @Column(name = "login", nullable = false)
    private String name;
    @Column (name = "email", nullable = false)
    private String email;
    @Column (name = "password", nullable = false)
    private String password;
}
