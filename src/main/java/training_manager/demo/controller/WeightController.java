package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.demo.dto.WeightDTO;
import training_manager.demo.service.entity.WeightService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WeightController {
    private final WeightService weightService;

    @PostMapping("/add_weight")
    public void addWeight(@RequestBody @Valid WeightDTO dto) {
        weightService.createFromDTO(dto);
    }

    @PostMapping("/show_weight")
    public List<WeightDTO> showWeight(@RequestBody Long userId) {
        return weightService.findAllUserWeight(userId);
    }

    @PostMapping("/del_weight")
    public void delWeight(@RequestBody Long id) {
        weightService.delete(id);
    }

    @PostMapping("/change_weight")
    public void changeWeight(@RequestBody @Valid WeightDTO dto) {
        weightService.update(dto);
    }

}
