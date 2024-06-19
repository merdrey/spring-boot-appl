package ru.bmstu.controller.statistic;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.dto.statistic.StatisticDTO;
import ru.bmstu.entities.player.Player;
import ru.bmstu.entities.statistics.Statistic;
import ru.bmstu.services.player.impl.PlayerServiceImpl;
import ru.bmstu.services.statistic.impl.StatisticServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticController {
    private final StatisticServiceImpl statService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/createStatPlayer")
    public ResponseEntity<?> create(@RequestBody StatisticDTO dto) {
        return new ResponseEntity<>(statService.create(dto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getAllStats")
    public ResponseEntity<List<Statistic>> getAll() {
        return new ResponseEntity<>(statService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getStat/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {

        return statService.getStat(id) != null
                ? new ResponseEntity<>(statService.getStat(id), HttpStatus.OK)
                : new ResponseEntity<>("Statistic with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteStat/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {

        return statService.deleteStat(id)
                ? new ResponseEntity<>("Statistic was deleted", HttpStatus.OK)
                : new ResponseEntity<>("Statistic with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/updateStat/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Integer id, @RequestParam(name = "status") Boolean stat) {
        return statService.updateStat(id, stat)
                ? new ResponseEntity<>(statService.getStat(id), HttpStatus.OK)
                : new ResponseEntity<>("Statistic with id " + id + " does not exist",HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getStat")
    public ResponseEntity<?> getByPlayer(@RequestParam(value = "name") String name) {
        for (Statistic stat : statService.getAll()) {
            if (stat.getPlayer().getNickname().equals(name)) {
                return new ResponseEntity<>(stat, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No statistics with player " + name, HttpStatus.NOT_FOUND);
    }
}
