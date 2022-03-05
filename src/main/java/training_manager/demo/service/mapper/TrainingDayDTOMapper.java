package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.TrainingDay;

@Data
@Component
public class TrainingDayDTOMapper implements EntityToDTOMapper<TrainingDayDTO, TrainingDay> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public TrainingDayDTO toDTO(TrainingDay entity, Object... args) {
        TrainingDayDTO dto = modelMapper.map(entity, TrainingDayDTO.class);
        if (entity.getMuscle() != null)
            dto.setMuscleGroup(entity.getMuscle().getMuscle());
        if (entity.getUser() != null)
            dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public TrainingDay toEntity(TrainingDayDTO dto, Object... args) {
        return modelMapper.map(dto, TrainingDay.class);
    }
}
