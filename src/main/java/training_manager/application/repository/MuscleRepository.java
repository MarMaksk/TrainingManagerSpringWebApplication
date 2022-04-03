package training_manager.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.application.entity.Muscle;
import training_manager.application.enums.MuscleGroupEnum;

import java.util.Optional;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
    Optional<Muscle> findByMuscleGroup(MuscleGroupEnum muscleGroup);
}
