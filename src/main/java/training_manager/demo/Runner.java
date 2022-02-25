package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.entity_service.BodyMeasurementService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class Runner {

    @Autowired
    UserRepository repository;

    @Autowired
    BodyMeasurementService service;

    @PostConstruct
    @Transactional
    public void test() {
        // BodyMeasurement bodyMeasurement = new BodyMeasurement();
        // bodyMeasurement.setUser(new User("Kolyan", "2222"));
        // service.create(bodyMeasurement);
        // bodyMeasurement.setCalves(255);
        // bodyMeasurement.setDate(LocalDate.now().minusDays(1L));
        BodyMeasurementDTO measurementUser = service.findBodyMeasurementUser(1l, LocalDate.now());
        measurementUser.setShoulder(5555);
        service.update(measurementUser);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
    }

}
