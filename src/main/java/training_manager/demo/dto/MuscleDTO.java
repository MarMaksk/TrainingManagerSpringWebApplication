package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.MuscleGroupEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuscleDTO {

    private MuscleGroupEnum muscleGroup;

}
