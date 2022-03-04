package training_manager.demo.service.entity_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.UserStatisticDTO;
import training_manager.demo.entity.UserStatistic;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.no_such.NoSuchUserStatisticException;
import training_manager.demo.repository.UserStatisticRepository;
import training_manager.demo.service.mapper.NullTrackingMapperDTO;
import training_manager.demo.service.mapper.UserStatisticDTOMapper;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatisticService implements CUDService<UserStatistic, UserStatisticDTO> {

    private final UserStatisticRepository repository;
    private final UserStatisticDTOMapper mapper;
    private final NullTrackingMapperDTO nullTrackingMapper;

    @Transactional
    public List<UserStatisticDTO> findALl() {
        return mapper.toDTOs(repository.findAll());
    }

    @Transactional
    public List<UserStatisticDTO> findAllStatisticUser(Long userId) {
        return mapper.toDTOs(repository.findAllByUserId(userId));
    }

    @Transactional
    public List<UserStatisticDTO> findAllStatsByUserIdAndDate(Long userId, LocalDate date) {
        return mapper.toDTOs(repository.findAllByUserIdAndDate(userId, date));
    }

    @Transactional
    public UserStatisticDTO findByUserIdAndDateAndMuscleGroup(Long userId, MuscleGroupEnum muscleGroup, LocalDate date) {
        UserStatistic userStatistic = repository.findByUserIdAndDataAndMuscleGroup(userId, muscleGroup, date)
                .orElseThrow(() -> new NoSuchUserStatisticException(
                        String.format("No such user statistic with user id %d and muscle group name %s and date %s",
                                userId, muscleGroup.name(), date.toString())
                ));
        return mapper.toDTO(userStatistic);
    }

    @Override
    public UserStatisticDTO create(UserStatistic entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public UserStatisticDTO update(UserStatisticDTO dto) {
        UserStatistic userStatistic = repository
                .findByUserIdAndDataAndMuscleGroup(dto.getUserId(), dto.getMuscleGroup(), dto.getDate())
                .orElseGet(UserStatistic::new);
        nullTrackingMapper.toEntity(userStatistic, dto);
        return mapper.toDTO(repository.save(userStatistic));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
