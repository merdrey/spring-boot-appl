package ru.bmstu.entities.statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bmstu.entities.maps.Map;
import ru.bmstu.entities.player.Player;

@Entity
@Table(name = "statistics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Statistic {
    @Id
    @Column(name = "s_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "p_id")
    private Player player;
    @Column (name = "status", nullable = false)
    private Boolean status;
}
