package ru.bmstu.dto.statistic;

import lombok.Data;

@Data
public class StatisticDTO {
    private Integer id;
    private Integer playerId;
    private Boolean status;
}
