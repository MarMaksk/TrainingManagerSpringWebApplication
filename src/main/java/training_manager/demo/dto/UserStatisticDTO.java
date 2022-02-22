package training_manager.demo.dto;

import lombok.Data;
import training_manager.demo.enums.MuscleGroupEnum;

import java.time.LocalDate;

@Data
public class UserStatisticDTO {
    private Long id;

    private LocalDate date;

    private int weight;

    private int repeats;

    private MuscleGroupEnum muscleGroup;

}
