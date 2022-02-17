package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.entity.UserStatistic;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.NoSuchUserStatisticException;
import training_manager.demo.repository.UserStatisticRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatisticService implements CUDService<UserStatistic> {

    private final UserStatisticRepository repository;

    public List<UserStatistic> findALl() {
        return repository.findAll();
    }

    public List<UserStatistic> findAllStatisticUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<UserStatistic> findAllStatsByUserIdAndDate(Long userId, LocalDate date) {
        return repository.findAllByUserIdAndDate(userId, date);
    }

    public UserStatistic findByUserIdAndDateAndMuscleGroup(Long userId, MuscleGroupEnum muscleGroup, LocalDate date) {
        return repository.findByUserIdAndDataAndMuscleGroup(userId, muscleGroup, date)
                .orElseThrow(() -> new NoSuchUserStatisticException(
                        String.format("No such user statistic with user id %d and muscle group name %s and date %s",
                                userId, muscleGroup.name(), date.toString())
                ));
    }

    @Override
    public UserStatistic create(UserStatistic entity) {
        return repository.save(entity);
    }

    @Override
    public UserStatistic update(UserStatistic entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(UserStatistic entity) {
        repository.delete(entity);
    }
}
