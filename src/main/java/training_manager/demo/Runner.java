package training_manager.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.entity.User;
import training_manager.demo.repository.UserRepository;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
        // UserRepository repository = context.getBean(UserRepository.class);

    }

}
