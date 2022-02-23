package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.RoleDTO;
import training_manager.demo.entity.Role;
import training_manager.demo.enums.RoleEnum;
import training_manager.demo.exception.NoSuchRoleException;
import training_manager.demo.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService implements CUDService<Role, RoleDTO> {

    private final RoleRepository repository;

    public Role findByRole(RoleEnum roleEnum) {
        return repository.findByRole(roleEnum).orElseThrow(() -> new NoSuchRoleException(
                String.format("No such role with name: %s", roleEnum.name())
        ));
    }

    @Override
    public RoleDTO create(Role entity) {
        Role role = repository.save(entity);
        return new RoleDTO(role.getId(), role.getRole());
    }

    @Override
    public RoleDTO update(RoleDTO entity) {
        throw new NoSuchRoleException();
    }

    @Override
    public void delete(RoleDTO entity) {
        repository.deleteById(findByRole(entity.getRole()).getId());
    }

}
