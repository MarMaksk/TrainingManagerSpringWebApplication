package training_manager.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import training_manager.demo.enums.RoleEnum;

@Data
@AllArgsConstructor
public class RoleDTO {

    private Long id;

    private RoleEnum role;

}
