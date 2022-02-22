package training_manager.demo.dto;

import lombok.Data;
import training_manager.demo.enums.MuscleGroupEnum;

import java.time.LocalDate;

@Data
public class TrainingDayDTO {

    private Long id;

    private int day;

    private MuscleGroupEnum muscle;

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
    private LocalDate lastDate = LocalDate.now();

    private Long userId;

}
