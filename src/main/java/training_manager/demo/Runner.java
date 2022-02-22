package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.entity.Muscle;
import training_manager.demo.entity.TrainingDay;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.repository.MuscleRepository;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.BodyMeasurementService;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
    }

}
