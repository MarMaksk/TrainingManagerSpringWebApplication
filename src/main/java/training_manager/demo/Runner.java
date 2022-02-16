package training_manager.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.entity.Muscle;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.repository.MuscleRepository;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.repository.UserRepository;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        User user = User.builder()
                .nickname("Maksim")
                .password("12345")
                .build();
        Muscle muscle = new Muscle(MuscleGroupEnum.BACK);
        TrainingDay trainingDay = new TrainingDay();
        trainingDay.addMuscle(muscle);
        trainingDay.setUser(user);
        trainingDay.setDay(1);
        trainingDay.setDescriptionExercises("new excesice");
        user.addTrainingDay(trainingDay);
        //userRepository.save(user);
        TrainingDayRepository trainingDayRepository = context.getBean(TrainingDayRepository.class);
        User maksim = userRepository.findByNickname("Maksim");
        MuscleRepository muscleRepository = context.getBean(MuscleRepository.class);
        Muscle muscleGroup = muscleRepository.findByMuscleGroup(MuscleGroupEnum.BACK);
        TrainingDay day = trainingDayRepository.findByUserAndDayAndMuscle(maksim, 1, muscleGroup);
        System.out.println(day);
    }

}
