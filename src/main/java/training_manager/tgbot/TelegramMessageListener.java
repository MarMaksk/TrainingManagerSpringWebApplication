package training_manager.tgbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import training_manager.application.dto.TrainingDayDTO;
import training_manager.application.service.entity.MuscleService;
import training_manager.tgbot.entity.TelegramUser;
import training_manager.tgbot.enums.UserStatus;
import training_manager.tgbot.service.AccountService;
import training_manager.tgbot.service.MessageForUser;
import training_manager.tgbot.service.TelegramUserService;
import training_manager.tgbot.service.TrainingTGService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TelegramMessageListener {

    private final TelegramBot bot;
    private final MessageForUser sendMessage;
    private final AccountService accountService;
    private final TelegramUserService userService;
    private final TrainingTGService tTGService;
    private final MuscleService muscleService;
    private TelegramUser user;

    /*
    Сделано: регистрация и авторизация, добавление тренировки, отображение всех треинровок
     */

    public void run() {
        bot.setUpdatesListener(lst -> {
            lst.forEach(update -> {
                new Thread(() -> {
                    checkMessage(update.message().text());
                    configureTelegramUser(update);
                    if (!user.isAuthorization()) {
                        responseForRegOrAuth();
                        responseWithStatusRegOrAuth();
                    }
                    responseWithoutStatus();
                    responseWithStatusTraining();


                    userService.addTelegramUser(user);
                }).start();
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void responseWithStatusTraining() {
        if (user.isResponseReceived())
            return;
        switch (userService.getUserCurrentUserStatus(user.getTelegramId())) {
            case ADD_TRAINING_1: {
                if (tTGService.createTraining(user.getTelegramId(), userService.getUsername(user.getTelegramId()), user.getMessage())) {
                    sendMessage.sendButtonGroup(
                            muscleService.findAllMuscle().stream()
                                    .map(muscle -> muscle.getMuscleGroup().toString())
                                    .collect(Collectors.toList())
                            , "Выберите группу мышц");
                    userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.ADD_TRAINING_2);
                } else
                    sendMessage.send("Данные введены неверно. Попробуйте ещё");
                break;
            }
            case ADD_TRAINING_2: {
                if (tTGService.saveTraining(user.getTelegramId(), user.getMessage())) {
                    sendMessage.send("Тренировка добавлена");
                    userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.NONE);
                } else {
                    sendMessage.send("Выбрана неверная группа мышц");
                }
                break;
            }
            case START_TRAINING: {
                List<TrainingDayDTO> dto = tTGService.startTraining(user.getMessage(), userService.getUsername(user.getTelegramId()));
                if (dto == null) {
                    sendMessage.send("Неверный день");
                } else {
                    dto.forEach(day -> {
                        sendMessage.send(String.format("День: %d, группа мышц: %s, описание: %s, " +
                                        "дата: %s, последнее число подходов: %d, последнее число повторений %d, " +
                                        "последний вес: %d",
                                day.getDay(),
                                day.getMuscleGroup(),
                                day.getDescriptionExercises(),
                                day.getLastDate().toString(),
                                day.getLastApproaches(),
                                day.getLastRepeats(),
                                day.getLastWeight()));
                    });
                }

            }
        }
    }

    private void responseWithoutStatus() {
        if (user.isResponseReceived() || isNonAuthorization())
            return;
        switch (user.getMessage()) {
            case "/addtraining":
                sendMessage.send("Введите данные в формате: день тренировки%описание тренировки");
                userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.ADD_TRAINING_1);
                break;
            case "/showtraining":
                Long userWebId = userService.getUserWebId(user.getTelegramId());
                List<TrainingDayDTO> trainingDayDTOs = tTGService.getAllTrainingDays(userWebId);
                trainingDayDTOs.forEach(day -> {
                    sendMessage.send(String.format("День: %d, группа мышц: %s, описание: %s, " +
                                    "дата: %s, последнее число подходов: %d, последнее число повторений %d, " +
                                    "последний вес: %d",
                            day.getDay(),
                            day.getMuscleGroup(),
                            day.getDescriptionExercises(),
                            day.getLastDate().toString(),
                            day.getLastApproaches(),
                            day.getLastRepeats(),
                            day.getLastWeight()));
                });
                break;
            case "/starttraining":
                sendMessage.send("Введите день тренировки");
                userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.START_TRAINING);
        }
    }

    private void responseWithStatusRegOrAuth() {
        if (user.isResponseReceived())
            return;
        switch (userService.getUserCurrentUserStatus(user.getTelegramId())) {
            case AUTHORIZATION: {
                if (accountService.authorization(user.getMessage(), user.getTelegramId())) {
                    sendMessage.send("Авторизация прошла успешно");
                    userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.NONE);
                    user.setAuthorization(true);
                } else
                    sendMessage.send("Некорректные данные авторизации");
                break;
            }
            case REGISTRATION: {
                if (accountService.registration(user.getMessage(), user.getTelegramId())) {
                    sendMessage.send("Регистрация прошла успешно");
                    userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.NONE);
                    user.setAuthorization(true);
                } else
                    sendMessage.send("Неверные входные данные");
                break;
            }
        }
    }


    private void responseForRegOrAuth() {
        switch (user.getMessage()) {
            case "/auth" -> {
                sendMessage.send("Введите логин пароль в формате: \"Логин пароль\"");
                userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.AUTHORIZATION);
            }
            case "/reg" -> {
                sendMessage.send("Введите логин пароль в формате: \"Логин пароль\"");
                userService.setUserCurrentUserStatus(user.getTelegramId(), UserStatus.REGISTRATION);
            }
        }
    }

    private boolean isNonAuthorization() {
        if (!user.isAuthorization()) {
            sendMessage.send("Треубется пройти авторизацию/регистрацию");
            return true;
        }
        return false;
    }

    private void checkMessage(String message) {
        if (message == null) {
            sendMessage.send("Бот читает только текст и символы");
        }
    }

    private void configureTelegramUser(Update update) {
        TelegramUser user = userService.getTelegramUser(update.message().from().id());
        if (user == null)
            user = TelegramUser.builder()
                    .telegramId(update.message().from().id())
                    .build();
        user.setMessage(update.message().text());
        user.setResponseReceived(false);
        sendMessage.setUser(user);
        this.user = user;
    }
}
