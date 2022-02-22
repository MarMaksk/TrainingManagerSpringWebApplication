package training_manager.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BodyMeasurementDTO {
    private Long id;

    private LocalDate date;

    private int chest;
    private int waist;
    private int hips;
    private int shoulder;
    private int thigh;
    private int calves;

    private Long userId;
}
