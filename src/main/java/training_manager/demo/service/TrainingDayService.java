package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.NoSuchTrainingDayException;
import training_manager.demo.repository.TrainingDayRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingDayService implements CUDService<TrainingDay> {

    private final TrainingDayRepository repository;

    public List<TrainingDay> findByUser(User user) {
        return repository.findAllByUserOrderByDay(user);
    }

    public TrainingDay findByUserAndDay(User user, int day) {
        return repository.findByUserAndDay(user, day).orElseThrow(() -> new NoSuchTrainingDayException(
                String.format("No such training day with user id %d and day %d", user.getId(), day)
        ));
    }

    public TrainingDay findByUserAndDayAndMuscleGroup(Long userId, int day, MuscleGroupEnum muscleGroup) {
        return repository.findByUserAndDayAndMuscleGroup(userId, day, muscleGroup)
                .orElseThrow(() -> new NoSuchTrainingDayException(
                        String.format("No such training day with user id %d and day %d and muscle group name %s",
                                userId, day, muscleGroup.name())
                ));
    }

    @Override
    public TrainingDay create(TrainingDay entity) {
        return repository.save(entity);
    }

    @Override
    public TrainingDay update(TrainingDay entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(TrainingDay entity) {
        repository.delete(entity);
    }
}
