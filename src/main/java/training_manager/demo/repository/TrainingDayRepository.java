package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.enums.MuscleGroupEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {

    List<TrainingDay> findAllByUserIdOrderByDay(Long id);

    Optional<TrainingDay> findByUserIdAndDay(Long id, int day);

    @Query("from TrainingDay td " +
            "join td.muscle m " +
            "join td.user u " +
            "where u.id = :userId and td.day = :day and m.muscleGroup = :muscleGroup")
    Optional<TrainingDay> findByUserIdAndDayAndMuscleGroup(@Param("userId") Long userId,
                                                           @Param("day") int day,
                                                           @Param("muscleGroup") MuscleGroupEnum muscleGroup);

   // Optional<TrainingDay> findByUserIdAndDayAndMuscleMuscleGroup(Long id, int day, MuscleGroupEnum muscle);

}
