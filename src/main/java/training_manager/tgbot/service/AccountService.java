package training_manager.tgbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import training_manager.application.entity.User;
import training_manager.application.exception.no_such.NoSuchUserException;
import training_manager.application.service.entity.UserService;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserService userService;

    public boolean authorization(String message, Long telegramId) {
        boolean result;
        try {
            String[] loginAndPassword = message.split(" ");
            result = userService.addTelegramIdToUser(loginAndPassword[0], loginAndPassword[1], telegramId);
        } catch (NoSuchUserException | IndexOutOfBoundsException ex) {
            result = false;
            log.warn(ex.getMessage());
        }
        return result;
    }

    public boolean registration(String message, Long telegramId) {
        boolean result;
        try {
            String[] loginAndPassword = message.split(" ");
            result = true;
            userService.create(new User(loginAndPassword[0], loginAndPassword[1], telegramId));
        } catch (IndexOutOfBoundsException ex) {
            result = false;
            log.warn(ex.getMessage());
        }
        return result;
    }
}
