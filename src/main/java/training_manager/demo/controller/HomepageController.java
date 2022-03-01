package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training_manager.demo.service.entity_service.TrainingDayService;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomepageController {

    private final TrainingDayService trainingDayService;

    @RequestMapping
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/show_training")
    public String showTraining(@RequestParam Long id, Model model) {
        model.addAttribute("trainings", trainingDayService.findByUserId(id));
        return "showTraining";
    }
}
