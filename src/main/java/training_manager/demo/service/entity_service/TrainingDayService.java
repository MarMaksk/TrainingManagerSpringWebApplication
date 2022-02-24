package training_manager.demo.service.entity_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.no_such.NoSuchTrainingDayException;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.service.mapper.TrainingDayDTOMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingDayService implements CUDService<TrainingDay, TrainingDayDTO> {

    private final TrainingDayRepository repository;

    private final TrainingDayDTOMapper mapper;

    public List<TrainingDayDTO> findByUser(User user) {
        return mapper.toDTOs(repository.findAllByUserOrderByDay(user));
    }

    public TrainingDayDTO findByUserAndDay(User user, int day) {
        TrainingDay trainingDay = repository.findByUserAndDay(user, day).orElseThrow(() -> new NoSuchTrainingDayException(
                String.format("No such training day with user id %d and day %d", user.getId(), day)
        ));
        return mapper.toDTO(trainingDay);
    }

    public TrainingDayDTO findByUserAndDayAndMuscleGroup(Long userId, int day, MuscleGroupEnum muscleGroup) {
        TrainingDay trainingDay = repository.findByUserAndDayAndMuscleGroup(userId, day, muscleGroup)
                .orElseThrow(() -> new NoSuchTrainingDayException(
                        String.format("No such training day with user id %d and day %d and muscle group name %s",
                                userId, day, muscleGroup.name())
                ));
        return mapper.toDTO(trainingDay);
    }

    @Override
    public TrainingDayDTO create(TrainingDay entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public TrainingDayDTO update(TrainingDayDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
