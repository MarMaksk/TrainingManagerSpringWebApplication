package training_manager.application.service.mapper;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training_manager.application.dto.UserStatisticDTO;
import training_manager.application.entity.UserStatistic;

@Component
@Data
public class UserStatisticDTOMapper implements EntityToDTOMapper<UserStatisticDTO, UserStatistic> {
    @Autowired
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
