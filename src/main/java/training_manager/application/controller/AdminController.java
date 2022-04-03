package training_manager.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training_manager.application.dto.UserDTO;
import training_manager.application.service.entity.RoleService;
import training_manager.application.service.entity.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    //    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/checkRoot")
    public boolean checkAdminRoot(Principal principal) {
        return roleService.checkAdminRootByNickname(principal.getName());
    }

    // @Secured(value = "ROLE_ADMIN")
    @GetMapping("/get_all_users")
    public List<UserDTO> getAllUsers() {
        return userService.findAllUser();
    }
}
