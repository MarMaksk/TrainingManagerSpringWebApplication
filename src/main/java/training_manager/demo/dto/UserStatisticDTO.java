package training_manager.demo.dto;

import lombok.Data;
import training_manager.demo.enums.MuscleGroupEnum;

import javax.persistence.PrePersist;
import java.time.LocalDate;

@Data
public class UserStatisticDTO {
    private Long id;

    private LocalDate date;

    private int weight;

    private int repeats;

    private MuscleGroupEnum muscleGroup;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }

}
