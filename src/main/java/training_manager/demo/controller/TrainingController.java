//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.service.entity.TrainingDayService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingDayService trainingDayService;

    @GetMapping({"/show_training"})
    public List<TrainingDayDTO> showTraining(@RequestParam Long id, Principal principal) {
        String username = principal.getName();
        return this.trainingDayService.findByUserId(id);
    }

    @PostMapping({"/change_training"})
    public TrainingDayDTO changeTraining(@RequestBody @Valid TrainingDayDTO training) {
        return trainingDayService.update(training);
    }

    @PostMapping({"/add_training"})
    public void addTraining(@RequestBody @Valid TrainingDayDTO training) {
        this.trainingDayService.createFromDTO(training);
    }

    @PostMapping({"/del_training"})
    public void deleteTraining(@RequestBody @Valid TrainingDayDTO training) {
        this.trainingDayService.delete(training.getId());
    }

    @PostMapping({"/start_training"})
    public List<TrainingDayDTO> startTraining(@RequestBody @Valid TrainingDayDTO training) {
        return this.trainingDayService.findByUserAndDayAndMuscleGroup(training.getUserId(), training.getDay(), training.getMuscleGroup());
    }
}
