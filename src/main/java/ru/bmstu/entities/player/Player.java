package ru.bmstu.entities.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bmstu.entities.user.User;

@Entity
@Table (name = "players")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Player {
    @Id
    @Column(name = "p_id")
    private Integer id;
    @Column (nullable = false, name = "nickname")
    private String nickname;
    @ManyToOne
    @JoinColumn(name = "u_id")
    private User user;
}
