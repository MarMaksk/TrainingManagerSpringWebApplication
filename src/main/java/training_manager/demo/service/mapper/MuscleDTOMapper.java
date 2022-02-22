package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.MuscleDTO;
import training_manager.demo.entity.Muscle;

@Component
@Data
public class MuscleDTOMapper implements EntityToDTOMapper<MuscleDTO, Muscle> {

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
