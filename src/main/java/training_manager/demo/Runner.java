package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.entity_service.BodyMeasurementService;
import training_manager.demo.service.entity_service.TrainingDayService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Runner {

    @Autowired
    UserRepository repository;

    @Autowired
    BodyMeasurementService service;

    @Autowired
    TrainingDayService tdservice;

    // TODO хибернейт кеш второго уровня
    //Переделать круд


    @PostConstruct
    @Transactional
    public void test() throws Exception {
        TrainingDay trainingDay = new TrainingDay();
        trainingDay.setDay(1);
        trainingDay.setLastApproaches(2);
        trainingDay.setUser(repository.getById(1l));
        trainingDay.setDescriptionExercises("CHEKC");
        tdservice.create(trainingDay);
        List<BodyMeasurementDTO> allByUser = service.findAllByUser(1L);
        System.out.println(allByUser);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
    }

}
