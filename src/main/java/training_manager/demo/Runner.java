package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.web.SecurityFilterChain;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.entity.BodyMeasurementService;
import training_manager.demo.service.entity.MuscleService;
import training_manager.demo.service.entity.TrainingDayService;

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

    @PostConstruct
    @Transactional
    public void test() throws Exception {
//        muscleService.create(new Muscle(MuscleGroupEnum.ABDOMINAL));
//        muscleService.create(new Muscle(MuscleGroupEnum.BICEPS));
//        muscleService.create(new Muscle(MuscleGroupEnum.TRICEPS));
//        muscleService.create(new Muscle(MuscleGroupEnum.BACK));
//        repository.save(new User("maksim", "TERMAXONATOR"));
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
    }

}
