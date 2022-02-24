package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.UserDTO;
import training_manager.demo.entity.User;
import training_manager.demo.service.entity_service.WeightService;

@Data
@Component
public class UserDTOMapper implements EntityToDTOMapper<UserDTO, User> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final WeightService weightService;

    @Override
    public UserDTO toDTO(User entity, Object... args) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        dto.setCurrentWeight(weightService.findByUserLastWeight(entity.getId()).getWeight());
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto, Object... args) {
        return modelMapper.map(dto, User.class);
    }
}
