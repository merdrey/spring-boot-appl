package ru.bmstu.services.player.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.dto.player.PlayerDTO;
import ru.bmstu.entities.player.Player;
import ru.bmstu.repository.PlayerRepository;
import ru.bmstu.repository.UserRepository;
import ru.bmstu.services.player.PlayerService;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    @Override
    public Player create(PlayerDTO playerDTO) {
        return playerRepository.save(Player.builder()
                .id(playerDTO.getId())
                .nickname(playerDTO.getNickname())
                .user(userRepository.getReferenceById(playerDTO.getUserId()))
                .build());
    }
    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }
    @Override
    public Player getPlayer(Integer id) {
        if(playerRepository.existsById(id)) {
            return playerRepository.getReferenceById(id);
        }
        return null;
    }
    @Override
    public boolean deletePlayer(Integer id) {
        if(playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean updatePlayer(Integer id, String name) {
        if(playerRepository.existsById(id)) {
            playerRepository.getReferenceById(id).setNickname(name);
            playerRepository.save(playerRepository.getReferenceById(id));
            return true;
        }
        return false;
    }
}
