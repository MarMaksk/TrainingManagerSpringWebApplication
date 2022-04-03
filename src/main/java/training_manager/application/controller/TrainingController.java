//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package training_manager.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training_manager.application.dto.TrainingDayDTO;
import training_manager.application.service.entity.TrainingDayService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingDayService trainingDayService;

    @GetMapping({"/show_training"})
    public List<TrainingDayDTO> showTraining(Principal principal) {
        return this.trainingDayService.findByUsername(principal.getName());
    }

    @PostMapping({"/change_training"})
    public TrainingDayDTO changeTraining(@RequestBody @Valid TrainingDayDTO training, Principal principal) {
        training.setUsername(principal.getName());
        return trainingDayService.update(training);
    }

    @PostMapping({"/add_training"})
    public void addTraining(@RequestBody @Valid TrainingDayDTO training, Principal principal) {
        training.setUsername(principal.getName());
        this.trainingDayService.createFromDTO(training);
    }

    @GetMapping({"/del_training"})
    public void deleteTraining(@RequestParam @Valid Long trainingId, Principal principal) {
        this.trainingDayService.delete(trainingId, principal.getName());
    }

    @PostMapping({"/start_training"})
    public List<TrainingDayDTO> startTraining(@RequestBody @Valid TrainingDayDTO training, Principal principal) {
        return this.trainingDayService.findByUserAndDayAndMuscleGroup(principal.getName(), training.getDay(), training.getMuscleGroup());
    }
}
