package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.service.UserSerivce;

@Data
@Component
public class BodyMeasurementDTOMapper implements EntityToDTOMapper<BodyMeasurementDTO, BodyMeasurement> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final UserSerivce userSerivce;

    @Override
    public BodyMeasurementDTO toDTO(BodyMeasurement entity, Object... args) {
        BodyMeasurementDTO dto = modelMapper.map(entity, BodyMeasurementDTO.class);
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public BodyMeasurement toEntity(BodyMeasurementDTO dto, Object... args) {
        BodyMeasurement entity = modelMapper.map(dto, BodyMeasurement.class);
        entity.setUser(userSerivce.findById(dto.getUserId()));
        return entity;
    }
}
