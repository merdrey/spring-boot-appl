package ru.bmstu.services.map;

import ru.bmstu.dto.map.MapDTO;
import ru.bmstu.entities.maps.Map;

import java.util.List;

public interface MapService {
    Map create(MapDTO mapDTO);
    List<Map> getAll();
    Map getMap(Integer id);
    boolean deleteMap(Integer id);
    boolean updateMap(Integer id, String name);
}
