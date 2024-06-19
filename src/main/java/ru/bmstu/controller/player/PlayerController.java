package ru.bmstu.controller.player;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.dto.player.PlayerDTO;
import ru.bmstu.entities.player.Player;
import ru.bmstu.services.player.impl.PlayerServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {
    private final PlayerServiceImpl playerService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/createPlayerByLogin")
    public ResponseEntity<?> create(@RequestBody PlayerDTO playerDTO) {
        return new ResponseEntity<>(playerService.create(playerDTO), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<Player>> getAll() {
        return new ResponseEntity<>(playerService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getPlayer/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {

        return playerService.getPlayer(id) != null
                ? new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK)
                : new ResponseEntity<>("Player with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletePlayer/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {

        return playerService.deletePlayer(id)
                ? new ResponseEntity<>("Player was deleted", HttpStatus.OK)
                : new ResponseEntity<>("Player with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/updatePlayer/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Integer id, @RequestParam(value = "name") String name) {
        return playerService.updatePlayer(id, name)
                ? new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK)
                : new ResponseEntity<>("Player with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getPlayer")
    public ResponseEntity<?> getByLogin(@RequestParam(value = "name") String name) {
        for(Player player : playerService.getAll()) {
            if(player.getNickname().equals(name)) {
                return new ResponseEntity<>(player, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No player with nickname " + name, HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletePlayer")
    public ResponseEntity<?> deleteByLogin(@RequestParam(value = "name") String name) {
        for(Player player : playerService.getAll()) {
            if(player.getNickname().equals(name)) {
                playerService.deletePlayer(player.getId());
                return new ResponseEntity<>("Player " + name + " deleted", HttpStatus.OK);

            }
        }
        return new ResponseEntity<>("No player with nickname " + name, HttpStatus.NOT_FOUND);
    }
}
