package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.demo.dto.WorkoutDTO;
import training_manager.demo.service.WorkoutService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/workout_save")
    public void workoutSave(@RequestBody @Valid WorkoutDTO dto) {
        workoutService.workoutSave(dto);
    }

}
