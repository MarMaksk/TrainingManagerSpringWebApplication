package training_manager.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.service.entity_service.BodyMeasurementService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class VolumeController {

    private final BodyMeasurementService bodyMeasurementService;

    @PostMapping("/add_body_measurement")
    private void addBodyMeasurement(@RequestBody BodyMeasurementDTO dto){
        bodyMeasurementService.createFromDTO(dto);
    }

}
