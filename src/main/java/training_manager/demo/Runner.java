package training_manager.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import training_manager.demo.entity.Role;
import training_manager.demo.entity.User;
import training_manager.demo.entity.UserInfo;
import training_manager.demo.enums.RoleEnum;
import training_manager.demo.repository.UserRepository;

@Slf4j
@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        UserRepository repository = context.getBean(UserRepository.class);
        User user = new User(UserInfo.builder()
                .nickname("Maksim")
                .password("12345")
                .build());
        repository.save(user);
    }

}
