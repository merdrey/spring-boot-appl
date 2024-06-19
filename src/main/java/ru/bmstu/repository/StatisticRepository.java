package ru.bmstu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.entities.statistics.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {
}
