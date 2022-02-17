package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.entity.User;
import training_manager.demo.entity.Weight;
import training_manager.demo.exception.NoSuchWeightException;
import training_manager.demo.repository.WeightRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeightService implements CUDService<Weight> {

    private final WeightRepository repository;

    public List<Weight> findAllUserWeight(User user) {
        return repository.findAllByUser(user);
    }

    public Weight findByUserAndDate(User user, LocalDate date) {
        return repository.findByUserAndDate(user, date).orElseThrow(() -> new NoSuchWeightException(
                String.format("No such weight with user id %d and date %s", user.getId(), date.toString())
        ));
    }

    @Override
    public Weight create(Weight entity) {
        return repository.save(entity);
    }

    @Override
    public Weight update(Weight entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Weight entity) {
        repository.delete(entity);
    }
}
