package training_manager.demo.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training_manager.demo.dto.UserStatisticDTO;
import training_manager.demo.entity.UserStatistic;

@Component
@Data
public class UserStatisticDTOMapper implements EntityToDTOMapper<UserStatisticDTO, UserStatistic> {

    private ModelMapper modelMapper;

    @Override
    public UserStatisticDTO toDTO(UserStatistic entity, Object... args) {
        UserStatisticDTO dto = modelMapper.map(entity, UserStatisticDTO.class);
        if (entity.getMuscle() != null)
            dto.setMuscleGroup(entity.getMuscle().getMuscleGroup());
        if (entity.getUser() != null)
            dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public UserStatistic toEntity(UserStatisticDTO dto, Object... args) {
        return modelMapper.map(dto, UserStatistic.class);
    }
}
