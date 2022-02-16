package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.Muscle;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {

    TrainingDay findByUser(User user);

    TrainingDay findByUserAndDay(User user, int day);

    //    @Query("select TrainingDay from TrainingDay td " +
//            "left join Muscle " +
//            "where td.user = :user and td.day = :day and Muscle.muscleGroup = :muscle")
    @Query("from TrainingDay td " +
            "where td.user = :user and td.day = :day and td.muscle = :muscle")
    TrainingDay findByUserAndDayAndMuscle(@Param("user") User user,
                                               @Param("day") int day,
                                               @Param("muscle") Muscle muscle);

    @Query("from TrainingDay td " +
            "join td.muscle m " +
            "where td.user = :user and td.day = :day and m.muscleGroup = :muscleGroup")
    TrainingDay findByUserAndDayAndMuscleGroup(@Param("user") User user,
                                               @Param("day") int day,
                                               @Param("muscleGroup") MuscleGroupEnum muscleGroup);

}
