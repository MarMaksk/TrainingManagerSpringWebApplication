package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.TrainingDay;

@Data
@Component
public class TrainingDayDTOMapper implements EntityToDTOMapper<TrainingDayDTO, TrainingDay> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TrainingDayDTO toDTO(TrainingDay entity, Object... args) {
        TrainingDayDTO dto = modelMapper.map(entity, TrainingDayDTO.class);
        dto.setMuscleGroup(entity.getMuscle().getMuscleGroup());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public TrainingDay toEntity(TrainingDayDTO dto, Object... args) {
        return modelMapper.map(dto, TrainingDay.class);
    }
}
