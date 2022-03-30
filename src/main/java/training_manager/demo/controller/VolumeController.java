package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.service.entity.BodyMeasurementService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class VolumeController {

    private final BodyMeasurementService bodyMeasurementService;

    @PostMapping("/add_body_measurement")
    public void addBodyMeasurement(@RequestBody @Valid BodyMeasurementDTO dto, Principal principal) {
        dto.setUsername(principal.getName());
        bodyMeasurementService.createFromDTO(dto);
    }

    @GetMapping("/show_body_measurement")
    public List<BodyMeasurementDTO> showBodyMeasurement(Principal principal) {
        return bodyMeasurementService.findAllByUser(principal.getName());
    }

    @GetMapping("/del_body_measurement")
    public void delBodyMeasurement(@RequestParam Long id, Principal principal) {
        bodyMeasurementService.delete(id, principal.getName());
    }

    @PostMapping("/change_body_measurement")
    public void changeBodyMeasurement(@RequestBody @Valid BodyMeasurementDTO dto, Principal principal) {
        dto.setUsername(principal.getName());
        bodyMeasurementService.update(dto);
    }

}
