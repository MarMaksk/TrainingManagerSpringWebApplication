package training_manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.application.repository.RoleRepository;
import training_manager.application.repository.UserRepository;
import training_manager.application.service.entity.BodyMeasurementService;
import training_manager.application.service.entity.MuscleService;
import training_manager.application.service.entity.TrainingDayService;
import training_manager.tgbot.TelegramMessageListener;

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

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TelegramMessageListener listener;

    @PostConstruct
    @Transactional
    public void test() throws Exception {
        listener.run();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        log.info("main started");
    }

}
