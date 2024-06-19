package ru.bmstu.services.player;

import ru.bmstu.dto.player.PlayerDTO;
import ru.bmstu.entities.player.Player;

import java.util.List;

public interface PlayerService {
    Player create(PlayerDTO playerDTO);
    List<Player> getAll();
    Player getPlayer(Integer id);
    boolean deletePlayer(Integer id);
    boolean updatePlayer(Integer id, String name);
}
