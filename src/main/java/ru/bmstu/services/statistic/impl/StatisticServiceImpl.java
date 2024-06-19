package ru.bmstu.services.statistic.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.dto.statistic.StatisticDTO;
import ru.bmstu.entities.statistics.Statistic;
import ru.bmstu.repository.MapRepository;
import ru.bmstu.repository.PlayerRepository;
import ru.bmstu.repository.StatisticRepository;
import ru.bmstu.services.statistic.StatisticService;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statRepo;
    private final MapRepository mapRepo;
    private final PlayerRepository playerRepo;
    @Override
    public Statistic create(StatisticDTO dto) {
        return statRepo.save(Statistic.builder()
                .id(dto.getId())
                .player(playerRepo.getReferenceById(dto.getPlayerId()))
                .status(dto.getStatus())
                .build());
    }
    @Override
    public List<Statistic> getAll() {
        return statRepo.findAll();
    }
    @Override
    public Statistic getStat(Integer id) {
        if(statRepo.existsById(id)) {
            return statRepo.getReferenceById(id);
        }
        return null;
    }
    @Override
    public boolean deleteStat(Integer id) {
        if(statRepo.existsById(id)) {
            statRepo.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateStat(Integer id, Boolean status) {
        if(statRepo.existsById(id)) {
            statRepo.getReferenceById(id).setStatus(status);
            statRepo.save(statRepo.getReferenceById(id));
            return true;
        }
        return false;
    }
}
