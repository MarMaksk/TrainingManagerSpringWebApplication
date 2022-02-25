package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.MuscleGroupEnum;

import javax.persistence.PrePersist;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticDTO {
    private Long id;

    private LocalDate date;

    private int weight;

    private int repeats;

    private MuscleGroupEnum muscleGroup;

    private Long userId;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }

}
