package training_manager.application.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.application.entity.TrainingDay;
import training_manager.application.enums.MuscleGroupEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {

    List<TrainingDay> findAllByUserUsernameOrderByDay(String username);

    List<TrainingDay> findByUserUsernameAndDay(String username, int day);

    List<TrainingDay> findAllByUserId(Long id);

    @Query("from TrainingDay td " +
            "join td.muscle m " +
            "join td.user u " +
            "where u.username = :username and td.day = :day and m.muscleGroup = :muscleGroup and td.id = :id")
    Optional<TrainingDay> findByIdUserIdAndDayAndMuscleGroup(@Param("id") Long id,
                                                             @Param("username") String username,
                                                             @Param("day") int day,
                                                             @Param("muscleGroup") MuscleGroupEnum muscleGroup);

    @Query("from TrainingDay td " +
            "join td.muscle m " +
            "join td.user u " +
            "where u.username = :username and td.day = :day and m.muscleGroup = :muscleGroup")
    List<TrainingDay> findByUserIdAndDayAndMuscleGroup(@Param("username") String username,
                                                             @Param("day") int day,
                                                             @Param("muscleGroup") MuscleGroupEnum muscleGroup);


    void deleteByIdAndUserUsername(Long id, String username);
}
