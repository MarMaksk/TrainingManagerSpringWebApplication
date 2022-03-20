package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeightDTO {

    private Long id;

    private LocalDate date;

    @Max(500)
    private int weight;

    private Long userId;

}
