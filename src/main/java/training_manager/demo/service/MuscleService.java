package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.entity.Muscle;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.NoSuchMuscleException;
import training_manager.demo.repository.MuscleRepository;

@Service
@RequiredArgsConstructor
public class MuscleService implements CUDService<Muscle> {

    private final MuscleRepository repository;

    public Muscle findMuscleByGroup(MuscleGroupEnum muscleGroup) {
        return repository.findByMuscleGroup(muscleGroup).orElseThrow(() -> new NoSuchMuscleException(
                String.format("No such muscle with muscle group: %s", muscleGroup.name())
        ));
    }

    @Override
    public Muscle create(Muscle entity) {
        return repository.save(entity);
    }

    @Override
    public Muscle update(Muscle entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Muscle entity) {
        repository.delete(entity);
    }
}
