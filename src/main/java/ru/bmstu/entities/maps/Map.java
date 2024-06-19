package ru.bmstu.entities.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bmstu.entities.player.Player;

@Entity
@Table(name = "maps")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Map {
    @Id
    @Column(name = "m_id")
    private Integer id;
    @Column(name = "m_name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "p_id")
    private Player player;
}
