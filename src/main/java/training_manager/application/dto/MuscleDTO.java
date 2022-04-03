package training_manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.application.enums.MuscleGroupEnum;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuscleDTO {

    @NotNull
    private MuscleGroupEnum muscleGroup;

}
