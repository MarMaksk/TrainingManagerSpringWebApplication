package training_manager.tgbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import training_manager.application.dto.MuscleDTO;
import training_manager.application.dto.TrainingDayDTO;
import training_manager.application.enums.MuscleGroupEnum;
import training_manager.application.service.entity.MuscleService;
import training_manager.application.service.entity.TrainingDayService;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingTGService {

    private final TelegramUserService tgUserService;
    private final TrainingDayService tdService;
    private final MuscleService muscleService;
    private Map<Long, TrainingDayDTO> dtoUsers = new HashMap<>();

    public List<TrainingDayDTO> getAllTrainingDays(Long userId) {
        return tdService.findByUserId(userId);
    }

    public boolean createTraining(Long telegramId, String username, String message) {
        String[] info = message.split("%");
        try {
            this.dtoUsers.put(telegramId,
                    TrainingDayDTO.builder()
                            .day(Integer.parseInt(info[0]))
                            .descriptionExercises(info[1])
                            .username(username)
                            .build()
            );
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveTraining(Long telegramId, String muscleGroup) {
        Optional<MuscleGroupEnum> res = muscleService.findAllMuscle().stream()
                .map(MuscleDTO::getMuscleGroup)
                .filter(muscle -> muscle.toString().equals(muscleGroup))
                .findFirst();
        if (res.isEmpty()){
            return false;
        } else {
            TrainingDayDTO dto = dtoUsers.get(telegramId);
            dto.setMuscleGroup(res.get());
            tdService.createFromDTO(dto);
            return true;
        }
    }

    public List<TrainingDayDTO> startTraining(String day, String username) {
        List<TrainingDayDTO> dto = null;
        try {
            dto = tdService.findByUserAndDay(username, Integer.parseInt(day));
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return dto;
    }

}
