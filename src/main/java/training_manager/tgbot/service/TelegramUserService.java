package training_manager.tgbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_manager.application.dto.UserDTO;
import training_manager.application.exception.no_such.NoSuchUserException;
import training_manager.application.service.entity.UserService;
import training_manager.tgbot.entity.TelegramUser;
import training_manager.tgbot.enums.UserStatus;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramUserService {
    private Map<Long, UserStatus> userStatus = new HashMap<>();
    private Map<Long, UserDTO> usersDTOs = new HashMap<>();
    private Map<Long, TelegramUser> telegramUsers = new HashMap<>();
    @Autowired
    private UserService userService;

    public Long getUserWebId(Long telegramId) {
        return getUserDTO(telegramId).getId();
    }

    public String getUsername(Long telegramId) {
        return getUserDTO(telegramId).getUsername();
    }

    public UserDTO getUserDTO(Long telegramId) {
        UserDTO userDTO;
        if (usersDTOs.get(telegramId) == null) {
            try {
                synchronized (usersDTOs) {
                    userDTO = userService.findByTelegramId(telegramId);
                    usersDTOs.put(telegramId, userDTO);
                }
            } catch (NoSuchUserException ex) {
                throw new NoSuchUserException("User not register or authorization");
            }
        } else {
            userDTO = usersDTOs.get(telegramId);
        }
        return userDTO;
    }

    public void setUserCurrentUserStatus(Long userId, UserStatus botState) {
        userStatus.put(userId, botState);
    }

    public UserStatus getUserCurrentUserStatus(Long userId) {
        return userStatus.get(userId) == null ? UserStatus.NONE : userStatus.get(userId);
    }

    public TelegramUser getTelegramUser(Long telegramId) {
        return telegramUsers.get(telegramId);
    }

    public void addTelegramUser(TelegramUser telegramUser) {
        telegramUsers.put(telegramUser.getTelegramId(), telegramUser);
    }
}
