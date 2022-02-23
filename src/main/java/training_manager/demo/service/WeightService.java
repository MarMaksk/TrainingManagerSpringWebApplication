package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.WeightDTO;
import training_manager.demo.entity.User;
import training_manager.demo.entity.Weight;
import training_manager.demo.exception.NoSuchWeightException;
import training_manager.demo.repository.WeightRepository;
import training_manager.demo.service.mapper.WeightDTOMapper;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeightService implements CUDService<Weight, WeightDTO> {

    private final WeightRepository repository;

    private final WeightDTOMapper mapper;

    public List<Weight> findAllUserWeight(Long userId) {
        return repository.findByUserId(userId);
    }

    public Weight findByUserAndDate(Long userId, LocalDate date) {
        return repository.findByUserIdAndDate(userId, date).orElseThrow(() -> new NoSuchWeightException(
                String.format("No such weight with user id %d and date %s", userId, date.toString())
        ));
    }

    public Weight findByUserLastWeight(Long id) {
        return repository.findFirstByUserIdOrderByDateDesc(id)
                .orElseThrow(NoSuchWeightException::new);
    }

    @Override
    public WeightDTO create(Weight entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public WeightDTO update(WeightDTO entity) {
        return mapper.toDTO(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public void delete(WeightDTO entity) {
        repository.delete(mapper.toEntity(entity));
    }
}
