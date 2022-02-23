package training_manager.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.UserDTO;
import training_manager.demo.entity.User;
import training_manager.demo.exception.NoSuchUserException;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.mapper.UserDTOMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements CUDService<User, UserDTO> {

    private final UserRepository repository;

    private final UserDTOMapper mapper;

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
    public UserDTO create(User entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public UserDTO update(UserDTO entity) {
        return mapper.toDTO(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public void delete(UserDTO entity) {
        repository.delete(mapper.toEntity(entity));
    }
}
