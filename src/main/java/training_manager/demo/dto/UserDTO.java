package training_manager.demo.dto;

import lombok.Data;
import training_manager.demo.enums.TrainingTypeEnum;

@Data
public class UserDTO {

    private Long id;

    private String nickname;

    private int currentWeight;

    private TrainingTypeEnum trainingType;
}
