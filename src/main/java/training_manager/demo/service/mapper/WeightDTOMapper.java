package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.WeightDTO;
import training_manager.demo.entity.Weight;

@Component
@Data
public class WeightDTOMapper implements EntityToDTOMapper<WeightDTO, Weight> {

    private ModelMapper modelMapper;

    @Override
    public WeightDTO toDTO(Weight entity, Object... args) {
        WeightDTO dto = modelMapper.map(entity, WeightDTO.class);
        if (entity.getUser() != null)
            dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public Weight toEntity(WeightDTO dto, Object... args) {
        return modelMapper.map(dto, Weight.class);
    }
}
