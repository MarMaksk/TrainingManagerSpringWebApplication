package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.entity.User;
import training_manager.demo.exception.NoSuchBodyMeasurementException;
import training_manager.demo.repository.BodyMeasurementRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyMeasurementService implements CUDService<BodyMeasurement> {

    private final BodyMeasurementRepository repository;

    public List<BodyMeasurement> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    public BodyMeasurement findBodyMeasurementUser(User user, LocalDate date) {
        return repository.findByUserAndDate(user, date).orElseThrow(() -> new NoSuchBodyMeasurementException(
                String.format("No such body measurement with user id: %d and date: %s", user.getId(), date.toString())
        ));
    }

    @Override
    public BodyMeasurement create(BodyMeasurement entity) {
        return repository.save(entity);
    }

    @Override
    public BodyMeasurement update(BodyMeasurement entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(BodyMeasurement entity) {
        repository.delete(entity);
    }
}
