package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.entity.Role;
import training_manager.demo.enums.RoleEnum;
import training_manager.demo.exception.NoSuchRoleException;
import training_manager.demo.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService implements CUDService<Role> {

    private final RoleRepository repository;

    public Role findByRole(RoleEnum roleEnum) {
        return repository.findByRole(roleEnum).orElseThrow(() -> new NoSuchRoleException(
                String.format("No such role with name: %s", roleEnum.name())
        ));
    }

    @Override
    public Role create(Role entity) {
        return repository.save(entity);
    }

    @Override
    public Role update(Role entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Role entity) {
        repository.delete(entity);
    }
}
