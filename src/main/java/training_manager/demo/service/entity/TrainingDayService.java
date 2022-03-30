package training_manager.demo.service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.no_such.NoSuchTrainingDayException;
import training_manager.demo.repository.MuscleRepository;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.mapper.NullTrackingMapperDTO;
import training_manager.demo.service.mapper.TrainingDayDTOMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingDayService implements CUDService<TrainingDay, TrainingDayDTO> {

    private final TrainingDayRepository repository;
    private final UserRepository userRepository;
    private final MuscleRepository muscleRepository;
    private final TrainingDayDTOMapper mapper;
    private final NullTrackingMapperDTO nullTrackingMapper;

    @Transactional
    public List<TrainingDayDTO> findByUserId(String username) {
        return mapper.toDTOs(repository.findAllByUserUsernameOrderByDay(username));
    }

    @Transactional
    public TrainingDayDTO findByUserAndDay(Long id, int day) {
        TrainingDay trainingDay = repository.findByUserIdAndDay(id, day).orElseThrow(() -> new NoSuchTrainingDayException(
                String.format("No such training day with user id %d and day %d", id, day)
        ));
        return mapper.toDTO(trainingDay);
    }

    @Transactional
    public List<TrainingDayDTO> findByUserAndDayAndMuscleGroup(String username, int day, MuscleGroupEnum muscleGroup) {
        List<TrainingDay> trainingDay = repository.findByUserIdAndDayAndMuscleGroup(username, day, muscleGroup);
        return mapper.toDTOs(trainingDay);
    }

    public void createFromDTO(TrainingDayDTO dto) {
        TrainingDay entity = mapper.toEntity(dto);
        entity.setUser(userRepository.findByUsername(dto.getUsername()).orElseThrow());
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
                .findByIdUserIdAndDayAndMuscleGroup(dto.getId(), dto.getUsername(), dto.getDay(), dto.getMuscleGroup())
                .orElseGet(TrainingDay::new);
        nullTrackingMapper.toEntity(trainingDay, dto);
        return mapper.toDTO(repository.save(trainingDay));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(Long id, String username) {
        repository.deleteByIdAndUserUsername(id, username);
    }
}

