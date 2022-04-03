package training_manager.application.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.application.dto.UserDTO;
import training_manager.application.entity.User;
import training_manager.application.service.entity.WeightService;

@Data
@Component
public class UserDTOMapper implements EntityToDTOMapper<UserDTO, User> {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final WeightService weightService;

    @Override
    public UserDTO toDTO(User entity, Object... args) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto, Object... args) {
        return modelMapper.map(dto, User.class);
    }
}
