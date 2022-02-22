package training_manager.demo.dto;

import lombok.Data;
import training_manager.demo.enums.MuscleGroupEnum;

@Data
public class MuscleDTO {

    private Long id;

    private MuscleGroupEnum muscleGroup;

}
