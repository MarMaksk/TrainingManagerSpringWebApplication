package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;

import java.time.DayOfWeek;

public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {

    TrainingDay findByUser(User user);

    TrainingDay findByUserAndDay(User user, int day);

    //TrainingDay findByUserAndDayAndMuscleGroup(User user, int day); // TODO: 15.02.2022
}
