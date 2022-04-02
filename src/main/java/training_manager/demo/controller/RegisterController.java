package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training_manager.demo.entity.User;
import training_manager.demo.service.entity.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@RequestParam String username, @RequestParam String password) {
        if (userService.existsUserByNickname(username)) {
            return "registration";
        } else {
            userService.create(new User(username, password));
            return "login";
        }
    }

}
