package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.UserStatisticDTO;
import training_manager.demo.entity.UserStatistic;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.NoSuchUserStatisticException;
import training_manager.demo.repository.UserStatisticRepository;
import training_manager.demo.service.mapper.UserStatisticDTOMapper;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatisticService implements CUDService<UserStatistic, UserStatisticDTO> {

    private final UserStatisticRepository repository;

    private final UserStatisticDTOMapper mapper;

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
    public UserStatisticDTO create(UserStatistic entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public UserStatisticDTO update(UserStatisticDTO entity) {
        return mapper.toDTO(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public void delete(UserStatisticDTO entity) {
        repository.delete(mapper.toEntity(entity));
    }
}
