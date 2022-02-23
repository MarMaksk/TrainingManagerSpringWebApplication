package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.NoSuchTrainingDayException;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.service.mapper.TrainingDayDTOMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingDayService implements CUDService<TrainingDay, TrainingDayDTO> {

    private final TrainingDayRepository repository;

    private final TrainingDayDTOMapper mapper;

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
    public TrainingDayDTO create(TrainingDay entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public TrainingDayDTO update(TrainingDayDTO entity) {
        return mapper.toDTO(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public void delete(TrainingDayDTO entity) {
        repository.delete(mapper.toEntity(entity));
    }
}
