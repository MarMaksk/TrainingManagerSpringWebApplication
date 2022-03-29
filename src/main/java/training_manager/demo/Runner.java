package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.web.SecurityFilterChain;
import training_manager.demo.entity.Muscle;
import training_manager.demo.entity.Role;
import training_manager.demo.entity.User;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.enums.RoleEnum;
import training_manager.demo.repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void test() throws Exception {
//        muscleService.create(new Muscle(MuscleGroupEnum.ABDOMINAL));
//        muscleService.create(new Muscle(MuscleGroupEnum.BICEPS));
//        muscleService.create(new Muscle(MuscleGroupEnum.TRICEPS));
//        muscleService.create(new Muscle(MuscleGroupEnum.BACK));
//        muscleService.create(new Muscle(MuscleGroupEnum.CHEST));
//        muscleService.create(new Muscle(MuscleGroupEnum.DELTOID));
//        muscleService.create(new Muscle(MuscleGroupEnum.LEG));
//        muscleService.create(new Muscle(MuscleGroupEnum.SHOULDERS));
//        roleRepository.save(new Role(RoleEnum.ROLE_USER));
//        roleRepository.save(new Role(RoleEnum.ROLE_ADMIN));
//        roleRepository.save(new Role(RoleEnum.ROLE_HELPER));
//        repository.save(new User("maksim", "TERMAXONATOR"));
//        repository.save(new User("newwwwwwww", "newwwwwwww"));
//        User user = repository.save(new User("meow-meow-meow", "meow-meow-meow"));
//        Role role = roleRepository.findByRole(RoleEnum.ROLE_ADMIN).get();
//        role.addUserToRole(user);
//        roleRepository.save(role);

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
    }

}
