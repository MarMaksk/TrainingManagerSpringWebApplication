package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.entity.User;
import training_manager.demo.exception.NoSuchUserException;
import training_manager.demo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSerivce implements CUDService<User> {

    private final UserRepository repository;

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchUserException(
                String.format("User with id %d not found", id)));
    }

    public User findByNickname(String nickname) {
        return repository.findByNickname(nickname).orElseThrow(() -> new NoSuchUserException(
                String.format("User with id %s not found", nickname)));
    }

    public List<User> findAllUser() {
        return repository.findAllByOrderByIdAsc();
    }

    public boolean existsUserByNickname(String nickname) {
        return repository.existsByNickname(nickname);
    }

    @Override
    public User create(User entity) {
        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(User entity) {
        repository.delete(entity);
    }
}
