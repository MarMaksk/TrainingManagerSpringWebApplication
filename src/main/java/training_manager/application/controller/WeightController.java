package training_manager.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training_manager.application.dto.WeightDTO;
import training_manager.application.service.entity.WeightService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WeightController {
    private final WeightService weightService;

    @PostMapping("/add_weight")
    public void addWeight(@RequestBody @Valid WeightDTO dto, Principal principal) {
        dto.setUsername(principal.getName());
        weightService.createFromDTO(dto);
    }

    @GetMapping("/show_weight")
    public List<WeightDTO> showWeight(Principal principal) {
        return weightService.findAllUserWeight(principal.getName());
    }

    @GetMapping("/del_weight")
    public void delWeight(@RequestParam Long id, Principal principal) {
        weightService.delete(id, principal.getName());
    }

    @PostMapping("/change_weight")
    public void changeWeight(@RequestBody @Valid WeightDTO dto, Principal principal) {
        dto.setUsername(principal.getName());
        weightService.update(dto);
    }

}
