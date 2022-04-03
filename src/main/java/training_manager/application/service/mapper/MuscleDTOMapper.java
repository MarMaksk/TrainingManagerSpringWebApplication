package training_manager.application.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.application.dto.MuscleDTO;
import training_manager.application.entity.Muscle;

@Component
@Data
public class MuscleDTOMapper implements EntityToDTOMapper<MuscleDTO, Muscle> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MuscleDTO toDTO(Muscle entity, Object... args) {
        MuscleDTO dto = modelMapper.map(entity, MuscleDTO.class);
        dto.setMuscleGroup(entity.getMuscleGroup());
        return dto;
    }

    @Override
    public Muscle toEntity(MuscleDTO dto, Object... args) {
        return modelMapper.map(dto, Muscle.class);
    }
}
