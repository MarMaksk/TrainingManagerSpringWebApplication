package training_manager.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.application.dto.MuscleDTO;
import training_manager.application.service.entity.MuscleService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MuscleTGService {
    private final MuscleService muscleService;

    public List<String> getAllMuscles() {
        List<String> muscles = new ArrayList<>();
        List<MuscleDTO> allMuscle = muscleService.findAllMuscle();
        for (MuscleDTO muscle : allMuscle)
            muscles.add(muscle.getMuscleGroup().toString());
        return muscles;
    }

}
