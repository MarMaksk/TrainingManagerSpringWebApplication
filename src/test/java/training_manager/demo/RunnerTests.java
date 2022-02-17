package training_manager.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import training_manager.demo.entity.User;
import training_manager.demo.repository.UserRepository;

@SpringBootTest
class RunnerTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void testOrphanRemoval() {

    }

    @Test
    void contextLoads() {
    }

    @Test
    void testUserRepository() {
        User maksim = userRepository.findByNickname("Maksim").get();
        System.out.println(maksim);
    }

}
