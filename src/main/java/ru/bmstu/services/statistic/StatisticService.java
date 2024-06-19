package ru.bmstu.services.statistic;

import ru.bmstu.dto.statistic.StatisticDTO;
import ru.bmstu.entities.statistics.Statistic;

import java.util.List;

public interface StatisticService {
    Statistic create(StatisticDTO dto);
    List<Statistic> getAll();
    Statistic getStat(Integer id);
    boolean deleteStat(Integer id);
    boolean updateStat(Integer id, Boolean status);
}
