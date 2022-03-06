package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.service.entity_service.UserService;

@Data
@Component
public class BodyMeasurementDTOMapper implements EntityToDTOMapper<BodyMeasurementDTO, BodyMeasurement> {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserService userService;

    @Override
    public BodyMeasurementDTO toDTO(BodyMeasurement entity, Object... args) {
        BodyMeasurementDTO dto = modelMapper.map(entity, BodyMeasurementDTO.class);
        if (entity.getUser() != null)
            dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public BodyMeasurement toEntity(BodyMeasurementDTO dto, Object... args) {
        return modelMapper.map(dto, BodyMeasurement.class);
    }
}
