package training_manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.application.enums.MuscleGroupEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDayDTO {

    private Long id;

    @NotNull
    private int day;

    @NotNull
    private MuscleGroupEnum muscleGroup;

    @NotBlank
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
    /**
     * Никнейм пользователя
     */
    private String username;

}
