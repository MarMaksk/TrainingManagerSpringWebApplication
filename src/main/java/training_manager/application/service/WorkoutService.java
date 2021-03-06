package training_manager.application.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training_manager.application.dto.WorkoutDTO;
import training_manager.application.entity.TrainingDay;
import training_manager.application.entity.UserStatistic;
import training_manager.application.exception.no_such.NoSuchTrainingDayException;
import training_manager.application.repository.TrainingDayRepository;
import training_manager.application.repository.UserStatisticRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Data
@Component
public class WorkoutService {

    ModelMapper modelMapper;
    TrainingDayRepository trainingDayRepository;
    UserStatisticRepository userStatisticRepository;

    @Transactional
    public void workoutSave(WorkoutDTO dto) {
        TrainingDay day = trainingDayRepository.findById(dto.getTrainingId()).orElseThrow(NoSuchTrainingDayException::new);
        day.setLastApproaches(dto.getApproaches());
        day.setLastRepeats(dto.getRepeats());
        day.setLastWeight(dto.getWeight());
        day.setLastDate(LocalDate.now());
        UserStatistic statistic = UserStatistic.builder()
                .date(LocalDate.now())
                .approaches(dto.getApproaches())
                .repeats(dto.getRepeats())
                .weight(dto.getWeight())
                .muscle(day.getMuscle())
                .user(day.getUser())
                .build();
        userStatisticRepository.save(statistic);
    }

}
