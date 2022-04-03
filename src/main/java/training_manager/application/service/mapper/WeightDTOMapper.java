package training_manager.application.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.application.dto.WeightDTO;
import training_manager.application.entity.Weight;
import training_manager.application.exception.no_such.NoSuchUserException;
import training_manager.application.repository.UserRepository;

@Component
@Data
public class WeightDTOMapper implements EntityToDTOMapper<WeightDTO, Weight> {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public WeightDTO toDTO(Weight entity, Object... args) {
        WeightDTO dto = modelMapper.map(entity, WeightDTO.class);
        if (entity.getUser() != null)
            dto.setUsername(entity.getUser().getUsername());
        return dto;
    }

    @Override
    public Weight toEntity(WeightDTO dto, Object... args) {
        Weight weight = modelMapper.map(dto, Weight.class);
        weight.setUser(userRepository.findByUsername(dto.getUsername()).orElseThrow(NoSuchUserException::new));
        return weight;
    }
}
