package ru.bmstu.services.map.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.dto.map.MapDTO;
import ru.bmstu.entities.maps.Map;
import ru.bmstu.repository.MapRepository;
import ru.bmstu.repository.PlayerRepository;
import ru.bmstu.services.map.MapService;

import java.util.List;

@Service
@AllArgsConstructor
public class MapServiceImpl implements MapService {
    private final MapRepository mapRepository;
    private final PlayerRepository playerRepo;
    @Override
    public Map create(MapDTO mapDTO) {
        return mapRepository.save(Map.builder()
                .id(mapDTO.getId())
                .name(mapDTO.getName())
                .player(playerRepo.getReferenceById(mapDTO.getPlayerId()))
                .build());
    }
    @Override
    public List<Map> getAll() {
        return mapRepository.findAll();
    }
    @Override
    public Map getMap(Integer id) {
        if(mapRepository.existsById(id)) {
            return mapRepository.getReferenceById(id);
        }
        return null;
    }
    @Override
    public boolean deleteMap(Integer id) {
        if(mapRepository.existsById(id)) {
            mapRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateMap(Integer id, String name) {
        if(mapRepository.existsById(id)) {
            mapRepository.getReferenceById(id).setName(name);
            mapRepository.save(mapRepository.getReferenceById(id));
            return true;
        }
        return false;
    }
}
