package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.MuscleDTO;
import training_manager.demo.entity.Muscle;

@Component
@Data
public class MuscleDTOMapper implements EntityToDTOMapper<MuscleDTO, Muscle> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MuscleDTO toDTO(Muscle entity, Object... args) {
        MuscleDTO dto = modelMapper.map(entity, MuscleDTO.class);
        dto.setMuscleGroup(entity.getMuscle());
        return dto;
    }

    @Override
    public Muscle toEntity(MuscleDTO dto, Object... args) {
        return modelMapper.map(dto, Muscle.class);
    }
}
