//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package training_manager.demo.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.service.entity_service.TrainingDayService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingDayService trainingDayService;

    @PostMapping({"/show_training"})
    public List<TrainingDayDTO> showTraining(@RequestBody Long id) {
        return this.trainingDayService.findByUserId(id);
    }

    @PostMapping({"/change_training"})
    public TrainingDayDTO changeTraining(@RequestBody TrainingDayDTO training) {
        return trainingDayService.update(training);
    }

    @PostMapping({"/add_training"})
    public void addTraining(@RequestBody TrainingDayDTO training) {
        this.trainingDayService.createFromDTO(training);
    }

    @PostMapping({"/del_training"})
    public void deleteTraining(@RequestBody TrainingDayDTO training) {
        this.trainingDayService.delete(training.getId());
    }
}
