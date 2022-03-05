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
public class TrainingDayDTO {

    private Long id;

    private int day;

    private MuscleGroupEnum muscleGroup;

    private String descriptionExercises;

    /**
     * Сколько было выполнено подходов в последний раз
     */
    private int lastApproaches;

    /**
     * Сколько было выполнено повторений
     */
    private int lastRepeats;
    /**
     * Последний вес упражнения
     */
    private int lastWeight;
    /**
     * Дата последнего занятия
     */
    private LocalDate lastDate;

    private Long userId;

    @PrePersist
    public void prePersist() {
        if (lastDate == null) {
            lastDate = LocalDate.now();
        }
    }
}
