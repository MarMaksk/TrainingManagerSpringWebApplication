package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.entity.*;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.repository.MuscleRepository;
import training_manager.demo.repository.TrainingDayRepository;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.repository.WeightRepository;
import training_manager.demo.service.BodyMeasurementService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Runner {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        TrainingDayRepository bean = context.getBean(TrainingDayRepository.class);
        WeightRepository weightRepository = context.getBean(WeightRepository.class);
        //user = new User("mask", "lazy");
        //weightRepository.save(new Weight(20, user));
        Weight weight = weightRepository.findByUserIdAndDate(2l, LocalDate.of(2022, 2, 23)).get();
        System.out.println(weight);
    }

}
