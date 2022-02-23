package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {

    List<TrainingDay> findAllByUserOrderByDay(User user);

    Optional<TrainingDay> findByUserAndDay(User user, int day);

    @Query("from TrainingDay td " +
            "join td.muscle m " +
            "join td.user u " +
            "where u.id = :userId and td.day = :day and m.muscleGroup = :muscleGroup")
    Optional<TrainingDay> findByUserAndDayAndMuscleGroup(@Param("userId") Long userId,
                                                         @Param("day") int day,
                                                         @Param("muscleGroup") MuscleGroupEnum muscleGroup);

}
