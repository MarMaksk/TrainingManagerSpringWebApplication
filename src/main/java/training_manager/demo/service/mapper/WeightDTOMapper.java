package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.WeightDTO;
import training_manager.demo.entity.Weight;
import training_manager.demo.repository.UserRepository;

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
            dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public Weight toEntity(WeightDTO dto, Object... args) {
        Weight weight = modelMapper.map(dto, Weight.class);
        weight.setUser(userRepository.getById(dto.getUserId()));
        return weight;
    }
}
