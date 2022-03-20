package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.service.entity.BodyMeasurementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class VolumeController {

    private final BodyMeasurementService bodyMeasurementService;

    @PostMapping("/add_body_measurement")
    public void addBodyMeasurement(@RequestBody @Valid BodyMeasurementDTO dto) {
        bodyMeasurementService.createFromDTO(dto);
    }

    @PostMapping("/show_body_measurement")
    public List<BodyMeasurementDTO> showBodyMeasurement(@RequestBody Long userId) {
        return bodyMeasurementService.findAllByUser(userId);
    }

    @PostMapping("/del_body_measurement")
    public void delBodyMeasurement(@RequestBody Long id) {
        bodyMeasurementService.delete(id);
    }

    @PostMapping("/change_body_measurement")
    public void changeBodyMeasurement(@RequestBody @Valid BodyMeasurementDTO dto) {
        bodyMeasurementService.update(dto);
    }

}
