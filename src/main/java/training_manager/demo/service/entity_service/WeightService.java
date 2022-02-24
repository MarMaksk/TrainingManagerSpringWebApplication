package training_manager.demo.service.entity_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.WeightDTO;
import training_manager.demo.entity.Weight;
import training_manager.demo.exception.no_such.NoSuchWeightException;
import training_manager.demo.repository.WeightRepository;
import training_manager.demo.service.mapper.WeightDTOMapper;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeightService implements CUDService<Weight, WeightDTO> {

    private final WeightRepository repository;

    private final WeightDTOMapper mapper;

    public List<WeightDTO> findAllUserWeight(Long userId) {
        return mapper.toDTOs(repository.findByUserId(userId));
    }

    public WeightDTO findByUserAndDate(Long userId, LocalDate date) {
        Weight weight = repository.findByUserIdAndDate(userId, date).orElseThrow(() -> new NoSuchWeightException(
                String.format("No such weight with user id %d and date %s", userId, date.toString())
        ));
        return mapper.toDTO(weight);
    }

    public WeightDTO findByUserLastWeight(Long id) {
        Weight weight = repository.findFirstByUserIdOrderByDateDesc(id)
                .orElseThrow(NoSuchWeightException::new);
        return mapper.toDTO(weight);
    }

    @Override
    public WeightDTO create(Weight entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public WeightDTO update(WeightDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
