package training_manager.demo.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import training_manager.demo.entity.Muscle;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;

@RequiredArgsConstructor
class TrainingDayRepositoryTest {

    @Autowired
    final UserRepository userRepository;
    @Autowired
    final TrainingDayRepository trainingDayRepository;

    @Test
    void findByUserAndDayAndMuscleGroupTest() {
        User user = User.builder()
                .nickname("Maksim")
                .password("12345")
                .build();
        Muscle muscle = new Muscle(MuscleGroupEnum.BACK);
        TrainingDay trainingDay = new TrainingDay();
        trainingDay.addMuscle(muscle);
        trainingDay.setUser(user);
        trainingDay.setDay(1);
        trainingDay.setDescriptionExercises("new excersice");
        user.addTrainingDay(trainingDay);
        userRepository.save(user);


    }
}