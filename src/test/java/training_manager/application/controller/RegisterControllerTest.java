package training_manager.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import training_manager.application.entity.User;
import training_manager.application.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    private User user;
    private User userSec = new User("CHECK" + Math.random() * Integer.MAX_VALUE, "CHECK");



    @BeforeEach
    void init() {
        user = userRepository.save(new User("CHECK" + Math.random() * Integer.MAX_VALUE, "CHECK"));
    }

    @Test
    void getRegistrationPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    void registerUserAccount() throws Exception {
        mockMvc.perform(post("/register/registration")
                .param("username", userSec.getUsername())
                .param("password", userSec.getPassword()))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void registerBusyUserAccount() throws Exception {
        mockMvc.perform(post("/register/registration")
                .param("username", user.getUsername())
                .param("password", user.getPassword()))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }
}