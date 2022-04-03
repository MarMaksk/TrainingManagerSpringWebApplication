package training_manager.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import training_manager.application.enums.RoleEnum;

@Data
@AllArgsConstructor
public class RoleDTO {

    private Long id;

    private RoleEnum role;

}
