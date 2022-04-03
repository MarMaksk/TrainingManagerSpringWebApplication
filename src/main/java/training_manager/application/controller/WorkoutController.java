package training_manager.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.application.dto.WorkoutDTO;
import training_manager.application.service.WorkoutService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/workout_save")
    public void workoutSave(@RequestBody @Valid WorkoutDTO dto, Principal principal) {
        dto.setUsername(principal.getName());
        workoutService.workoutSave(dto);
    }

}
