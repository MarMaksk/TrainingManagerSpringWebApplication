package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.MuscleGroupEnum;

import javax.validation.constraints.Max;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticDTO {
    private Long id;

    private LocalDate date;

    @Max(500)
    private int weight;

    private int repeats;

    private MuscleGroupEnum muscleGroup;

    private Long userId;

}
