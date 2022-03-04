package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import training_manager.demo.dto.TrainingDayDTO;
import training_manager.demo.service.entity_service.TrainingDayService;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomepageController {

    private final TrainingDayService trainingDayService;

    @RequestMapping
    public String homepage() {
        return "homepage";
    }

    @PostMapping("/show_training")
    @ResponseBody
    public List<TrainingDayDTO> showTraining(@RequestBody Long id) {
        return trainingDayService.findByUserId(id);
    }

    @PostMapping("/change_training")
    @ResponseBody
    public TrainingDayDTO changeTraining(@RequestBody TrainingDayDTO training) {
        return trainingDayService.update(training);
    }
}
