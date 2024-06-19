package ru.bmstu.dto.player;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PlayerDTO {
    private Integer id;
    private String nickname;
    private Integer userId;
}
