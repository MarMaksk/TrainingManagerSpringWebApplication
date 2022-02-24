package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.TrainingTypeEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String nickname;

    private int currentWeight;

    private TrainingTypeEnum trainingType;
}
