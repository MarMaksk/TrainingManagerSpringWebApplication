package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.MuscleGroupEnum;

import javax.validation.constraints.Max;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDTO {

    @Max(500)
    private int weight;

    private int repeats;

    @Max(100)
    private int approaches;

    private MuscleGroupEnum muscleGroup;

    private String username;

    private Long trainingId;
}
