package training_manager.demo.service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.RoleDTO;
import training_manager.demo.entity.Role;
import training_manager.demo.enums.RoleEnum;
import training_manager.demo.exception.no_such.NoSuchRoleException;
import training_manager.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService implements CUDService<Role, RoleDTO> {

    private final RoleRepository repository;

    public boolean checkAdminRootByNickname(String username) {
        Set<Role> roles = repository.findByUsersUsername(username);
        return roles.stream().anyMatch(r -> r.getRole().equals(RoleEnum.ADMIN));
    }

    @Transactional
    public RoleDTO findByRole(RoleEnum roleEnum) {
        Role role = repository.findByRole(roleEnum).orElseThrow(() -> new NoSuchRoleException(
                String.format("No such role with name: %s", roleEnum.name())
        ));
        return new RoleDTO(role.getId(), role.getRole());
    }

    @Override
    public RoleDTO create(Role entity) {
        Role role = repository.save(entity);
        return new RoleDTO(role.getId(), role.getRole());
    }

    @Override
    @Deprecated
    public RoleDTO update(RoleDTO dto) {
        throw new NoSuchRoleException();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
