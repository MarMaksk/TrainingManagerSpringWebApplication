package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.entity.Muscle;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.entity_service.BodyMeasurementService;
import training_manager.demo.service.entity_service.MuscleService;
import training_manager.demo.service.entity_service.TrainingDayService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Slf4j
@SpringBootApplication
public class Runner {

    @Autowired
    UserRepository repository;

    @Autowired
    BodyMeasurementService service;

    @Autowired
    TrainingDayService tdservice;

    @Autowired
    MuscleService muscleService;

    // TODO хибернейт кеш второго уровня
    // Переделать круд
    // Добавить в сервисы смену пользователя


    @PostConstruct
    @Transactional
    public void test() throws Exception {
        muscleService.create(new Muscle(MuscleGroupEnum.ABDOMINAL));
        muscleService.create(new Muscle(MuscleGroupEnum.BICEPS));
        muscleService.create(new Muscle(MuscleGroupEnum.TRICEPS));
        muscleService.create(new Muscle(MuscleGroupEnum.BACK));
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
    }

}
