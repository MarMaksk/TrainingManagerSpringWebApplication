package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @PrePersist
    public void prePersist() {
        if (date == null) {
            setDate(LocalDate.now());
        }
    }
}
