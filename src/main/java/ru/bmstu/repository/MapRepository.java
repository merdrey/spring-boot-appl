package ru.bmstu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.entities.maps.Map;

public interface MapRepository extends JpaRepository<Map, Integer> {
}
