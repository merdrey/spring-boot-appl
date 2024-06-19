package ru.bmstu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.entities.player.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
