package training_manager.tgbot.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TelegramUser {
    private Long telegramId;

    private String message;

    private boolean responseReceived;

    private boolean authorization;
}
