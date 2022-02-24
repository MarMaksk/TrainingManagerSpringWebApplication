package training_manager.demo.dto;

import lombok.Data;

import javax.persistence.PrePersist;
import java.time.LocalDate;

@Data
public class WeightDTO {

    private Long id;

    private LocalDate localDate;

    private int weight;

    private Long userId;

    @PrePersist
    public void prePersist() {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
    }

}
