package training_manager.tgbot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import training_manager.tgbot.entity.TelegramUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageForUser {

    private final TelegramBot bot;
    @Setter
    private TelegramUser user;
    private final TelegramUserService userService;

    public void send(String message) {
        ReplyKeyboardRemove rkr = new ReplyKeyboardRemove();
        bot.execute(new SendMessage(user.getTelegramId(), message).replyMarkup(rkr));
        user.setResponseReceived(true);
    }

    public void sendButtonGroup(List<String> list, String message) {
        KeyboardButton[] keyboardButtons = new KeyboardButton[list.size()];
        for (int i = 0; i < keyboardButtons.length; i++) {
            keyboardButtons[i] = new KeyboardButton(list.get(i));
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup("");
        for (KeyboardButton kb : keyboardButtons) {
            replyKeyboardMarkup.addRow(kb);
        }
        replyKeyboardMarkup.resizeKeyboard(false).selective(true).oneTimeKeyboard(true);
        bot.execute(new SendMessage(user.getTelegramId(), message).replyMarkup(replyKeyboardMarkup));
        user.setResponseReceived(true);
    }
}
