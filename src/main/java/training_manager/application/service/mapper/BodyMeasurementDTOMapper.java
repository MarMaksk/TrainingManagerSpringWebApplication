package training_manager.application.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.application.dto.BodyMeasurementDTO;
import training_manager.application.entity.BodyMeasurement;
import training_manager.application.repository.UserRepository;

@Data
@Component
public class BodyMeasurementDTOMapper implements EntityToDTOMapper<BodyMeasurementDTO, BodyMeasurement> {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public BodyMeasurementDTO toDTO(BodyMeasurement entity, Object... args) {
        BodyMeasurementDTO dto = modelMapper.map(entity, BodyMeasurementDTO.class);
        if (entity.getUser() != null)
            dto.setUsername(entity.getUser().getUsername());
        return dto;
    }

    @Override
    public BodyMeasurement toEntity(BodyMeasurementDTO dto, Object... args) {
        BodyMeasurement bodyMeasurement = modelMapper.map(dto, BodyMeasurement.class);
        bodyMeasurement.setUser(userRepository.findByUsername(dto.getUsername()).orElseThrow());
        return bodyMeasurement;
    }
}
