package training_manager.demo.service.entity_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.no_such.NoSuchTrainingDayException;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.service.mapper.NullTrackingMapperDTO;
import training_manager.demo.service.mapper.TrainingDayDTOMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingDayService implements CUDService<TrainingDay, TrainingDayDTO> {

    private final TrainingDayRepository repository;
    private final TrainingDayDTOMapper mapper;
    private final NullTrackingMapperDTO nullTrackingMapper;

    @Transactional
    public List<TrainingDayDTO> findByUser(User user) {
        return mapper.toDTOs(repository.findAllByUserOrderByDay(user));
    }

    @Transactional
    public TrainingDayDTO findByUserAndDay(User user, int day) {
        TrainingDay trainingDay = repository.findByUserAndDay(user, day).orElseThrow(() -> new NoSuchTrainingDayException(
                String.format("No such training day with user id %d and day %d", user.getId(), day)
        ));
        return mapper.toDTO(trainingDay);
    }

    @Transactional
    public TrainingDayDTO findByUserAndDayAndMuscleGroup(Long userId, int day, MuscleGroupEnum muscleGroup) {
        TrainingDay trainingDay = repository.findByUserIdAndDayAndMuscleGroup(userId, day, muscleGroup)
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
        TrainingDay trainingDay = repository
                .findByUserIdAndDayAndMuscleGroup(dto.getUserId(), dto.getDay(), dto.getMuscle())
                .orElseGet(TrainingDay::new);
        TrainingDay entity = nullTrackingMapper.toEntity(trainingDay, dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
