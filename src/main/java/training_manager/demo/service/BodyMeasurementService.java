package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.entity.User;
import training_manager.demo.exception.NoSuchBodyMeasurementException;
import training_manager.demo.repository.BodyMeasurementRepository;
import training_manager.demo.service.mapper.BodyMeasurementDTOMapper;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyMeasurementService implements CUDService<BodyMeasurement, BodyMeasurementDTO> {

    private final BodyMeasurementRepository repository;

    private final BodyMeasurementDTOMapper mapper;

    public List<BodyMeasurementDTO> findAllByUser(User user) {
        return mapper.toDTOs(repository.findAllByUser(user));
    }

    public BodyMeasurementDTO findBodyMeasurementUser(User user, LocalDate date) {
        BodyMeasurement measurement = repository.findByUserAndDate(user, date).orElseThrow(() -> new NoSuchBodyMeasurementException(
                String.format("No such body measurement with user id: %d and date: %s", user.getId(), date.toString())
        ));
        return mapper.toDTO(measurement);
    }

    @Override
    public BodyMeasurementDTO create(BodyMeasurement entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public BodyMeasurementDTO update(BodyMeasurementDTO entity) {
        return mapper.toDTO(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public void delete(BodyMeasurementDTO entity) {
        repository.delete(mapper.toEntity(entity));
    }
}
