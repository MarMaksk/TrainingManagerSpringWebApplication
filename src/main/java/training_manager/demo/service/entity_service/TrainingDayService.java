package training_manager.demo.service.entity_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.no_such.NoSuchTrainingDayException;
import training_manager.demo.exception.no_such.NoSuchUserException;
import training_manager.demo.repository.MuscleRepository;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.mapper.NullTrackingMapperDTO;
import training_manager.demo.service.mapper.TrainingDayDTOMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingDayService implements CUDService<TrainingDay, TrainingDayDTO> {

    private final TrainingDayRepository repository;
    private final UserRepository userRepository;
    private final MuscleRepository muscleRepository;
    private final TrainingDayDTOMapper mapper;
    private final NullTrackingMapperDTO nullTrackingMapper;

    @Transactional
    public List<TrainingDayDTO> findByUserId(Long id) {
        return mapper.toDTOs(repository.findAllByUserIdOrderByDay(id));
    }

    @Transactional
    public TrainingDayDTO findByUserAndDay(Long id, int day) {
        TrainingDay trainingDay = repository.findByUserIdAndDay(id, day).orElseThrow(() -> new NoSuchTrainingDayException(
                String.format("No such training day with user id %d and day %d", id, day)
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

    public void createFromDTO(TrainingDayDTO dto) {
        TrainingDay entity = mapper.toEntity(dto);
        entity.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        entity.setMuscle(muscleRepository.findByMuscleGroup(dto.getMuscleGroup()).orElseThrow());
        repository.save(entity);
    }

    @Override
    public TrainingDayDTO create(TrainingDay entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public TrainingDayDTO update(TrainingDayDTO dto) {
        TrainingDay trainingDay = repository
                .findByUserIdAndDayAndMuscleGroup(dto.getUserId(), dto.getDay(), dto.getMuscleGroup())
                .orElseGet(TrainingDay::new);
        nullTrackingMapper.toEntity(trainingDay, dto);
//        trainingDay.setUser(userRepository.findById(dto.getUserId()).orElseThrow(NoSuchUserException::new));
//        trainingDay.setMuscle(muscleRepository.findByMuscle(dto.getMuscleGroup()).orElseThrow());
        return mapper.toDTO(repository.save(trainingDay));
    }

    @Override
    public void delete(Long id) {
        Optional<TrainingDay> day = repository.findById(id);
        repository.delete(day.get());
    }
}

