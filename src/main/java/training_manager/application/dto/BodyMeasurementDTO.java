package training_manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyMeasurementDTO {
    private Long id;

    @NotNull
    private LocalDate date;

    @Max(250)
    private int chest;
    @Max(250)
    private int waist;
    @Max(250)
    private int hips;
    @Max(250)
    private int shoulder;
    @Max(250)
    private int thigh;
    @Max(250)
    private int calves;

    private String username;

}
