package ru.bmstu.controller.map;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.dto.map.MapDTO;
import ru.bmstu.entities.maps.Map;
import ru.bmstu.services.map.impl.MapServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/maps")
@AllArgsConstructor
public class MapController {
    private final MapServiceImpl mapService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/createMapPlayer")
    public ResponseEntity<?> create(@RequestBody MapDTO mapDTO) {
        return new ResponseEntity<>(mapService.create(mapDTO), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getAllMaps")
    public ResponseEntity<List<Map>> getAll() {
        return new ResponseEntity<>(mapService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getMap/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {

        return mapService.getMap(id) != null
                ? new ResponseEntity<>(mapService.getMap(id), HttpStatus.OK)
                : new ResponseEntity<>("Map with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteMap/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {

        return mapService.deleteMap(id)
                ? new ResponseEntity<>("Map was deleted", HttpStatus.OK)
                : new ResponseEntity<>("Map with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/updateMap/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Integer id, @RequestParam(value = "name") String name) {
        return mapService.updateMap(id, name)
                ? new ResponseEntity<>(mapService.getMap(id), HttpStatus.OK)
                : new ResponseEntity<>("Map with id " + id + " does not exist",HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getMap")
    public ResponseEntity<?> getByPlayer(@RequestParam(value = "name") String name) {
        for (Map map : mapService.getAll()) {
                    if (map.getPlayer().getNickname().equals(name)) {
                        return new ResponseEntity<>(map, HttpStatus.OK);
                    }
                }
        return new ResponseEntity<>("No map with player " + name, HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteMap")
    public ResponseEntity<?> deleteByName(@RequestParam(value = "name") String name) {
        for(Map map : mapService.getAll()) {
            if(map.getName().equals(name)) {
                mapService.deleteMap(map.getId());
                return new ResponseEntity<>("Map " + name + " deleted", HttpStatus.OK);

            }
        }
        return new ResponseEntity<>("No map with name " + name, HttpStatus.NOT_FOUND);
    }
}
