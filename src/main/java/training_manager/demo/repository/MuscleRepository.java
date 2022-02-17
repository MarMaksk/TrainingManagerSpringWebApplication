package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.Muscle;
import training_manager.demo.enums.MuscleGroupEnum;

import java.util.Optional;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
    Optional<Muscle> findByMuscleGroup(MuscleGroupEnum muscleGroupEnum);
}
