package training_manager.demo.dto;

import lombok.Data;
import training_manager.demo.enums.RoleEnum;

@Data
public class RoleDTO {

    private Long id;

    private RoleEnum role;

}
