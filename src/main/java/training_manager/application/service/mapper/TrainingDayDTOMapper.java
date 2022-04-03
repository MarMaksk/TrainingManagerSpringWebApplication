package training_manager.application.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.application.dto.TrainingDayDTO;
import training_manager.application.entity.TrainingDay;
import training_manager.application.repository.MuscleRepository;
import training_manager.application.repository.UserRepository;

@Data
@Component
public class TrainingDayDTOMapper implements EntityToDTOMapper<TrainingDayDTO, TrainingDay> {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MuscleRepository muscleRepository;
    @Override
    public TrainingDayDTO toDTO(TrainingDay entity, Object... args) {
        TrainingDayDTO dto = modelMapper.map(entity, TrainingDayDTO.class);
        dto.setMuscleGroup(entity.getMuscle().getMuscleGroup());
        dto.setUsername(entity.getUser().getUsername());
        return dto;
    }

    @Override
    public TrainingDay toEntity(TrainingDayDTO dto, Object... args) {
        TrainingDay entity = modelMapper.map(dto, TrainingDay.class);
        entity.setUser(userRepository.findByUsername(dto.getUsername()).orElseThrow());
        entity.setMuscle(muscleRepository.findByMuscleGroup(dto.getMuscleGroup()).orElseThrow());
        return entity;
    }
}
