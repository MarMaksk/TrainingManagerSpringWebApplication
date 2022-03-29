package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.demo.dto.MuscleDTO;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.service.entity.MuscleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuxiliaryController {

    private final MuscleService muscleService;

    @GetMapping("/muscle_group")
    public List<MuscleGroupEnum> showAllMuscleGroup() {
        return muscleService.findAllMuscle().stream().map(MuscleDTO::getMuscleGroup).collect(Collectors.toList());
    }
}
