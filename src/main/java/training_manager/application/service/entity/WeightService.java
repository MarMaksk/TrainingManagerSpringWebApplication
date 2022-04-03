package training_manager.application.service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.application.dto.WeightDTO;
import training_manager.application.entity.User;
import training_manager.application.entity.Weight;
import training_manager.application.exception.no_such.NoSuchUserException;
import training_manager.application.exception.no_such.NoSuchWeightException;
import training_manager.application.repository.UserRepository;
import training_manager.application.repository.WeightRepository;
import training_manager.application.service.mapper.NullTrackingMapperDTO;
import training_manager.application.service.mapper.WeightDTOMapper;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeightService implements CUDService<Weight, WeightDTO> {

    private final WeightRepository repository;
    private final WeightDTOMapper mapper;
    private final UserRepository userRepository;
    private final NullTrackingMapperDTO nullTrackingMapper;

    @Transactional
    public List<WeightDTO> findAllUserWeight(String username) {
        return mapper.toDTOs(repository.findByUsername(username));
    }

    @Transactional
    public WeightDTO findByUserAndDate(String username, LocalDate date) {
        Weight weight = repository.findByUserUsernameAndDate(username, date).orElseThrow(() -> new NoSuchWeightException(
                String.format("No such weight with user id %s and date %s", username, date.toString())
        ));
        return mapper.toDTO(weight);
    }

    @Transactional
    public WeightDTO findByUserLastWeight(Long id) {
        Weight weight = repository.findFirstByUserIdOrderByDateDesc(id)
                .orElseThrow(NoSuchWeightException::new);
        return mapper.toDTO(weight);
    }

    @Transactional
    public WeightDTO createFromDTO(WeightDTO dto) {
        Weight entity = mapper.toEntity(dto);
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(NoSuchUserException::new);
        user.setHeight(entity.getWeight());
        userRepository.save(user);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public WeightDTO create(Weight entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public WeightDTO update(WeightDTO dto) {
        Weight weight = repository.findByUserUsernameAndDate(dto.getUsername(), dto.getDate()).orElseGet(Weight::new);
        nullTrackingMapper.toEntity(weight, dto);
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(NoSuchUserException::new);
        user.setHeight(weight.getWeight());
        userRepository.save(user);
        return mapper.toDTO(repository.save(weight));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(Long id, String username) {
        repository.deleteByIdAndUserUsername(id, username);
    }
}
