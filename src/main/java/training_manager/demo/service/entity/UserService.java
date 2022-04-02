package training_manager.demo.service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.UserDTO;
import training_manager.demo.entity.User;
import training_manager.demo.exception.no_such.NoSuchUserException;
import training_manager.demo.repository.UserRepository;
import training_manager.demo.service.mapper.NullTrackingMapperDTO;
import training_manager.demo.service.mapper.UserDTOMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements CUDService<User, UserDTO> {

    private final UserRepository repository;
    private final UserDTOMapper mapper;
    private final NullTrackingMapperDTO nullTrackingMapper;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NoSuchUserException(
                String.format("User with id %d not found", id)));
        return mapper.toDTO(user);
    }

    @Transactional
    public UserDTO findByNickname(String nickname) {
        User user = repository.findByUsername(nickname).orElseThrow(() -> new NoSuchUserException(
                String.format("User with id %s not found", nickname)));
        return mapper.toDTO(user);
    }

    @Transactional
    public List<UserDTO> findAllUser() {
        return mapper.toDTOs(repository.findAllByOrderByIdAsc());
    }

    @Transactional
    public boolean existsUserByNickname(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public UserDTO create(User entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public UserDTO update(UserDTO dto) {
        User user = repository.findById(dto.getId()).orElseThrow(NoSuchUserException::new);
        nullTrackingMapper.toEntity(user, dto);
        return mapper.toDTO(repository.save(user));
    }

    @Override
    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(NoSuchUserException::new);
        user.setDeleted(true);
        repository.save(user);
    }

    @Transactional
    public void delete(Long id, String username) {
        User user = repository.findByIdAndUsername(id, username).orElseThrow(NoSuchUserException::new);
        user.setDeleted(true);
        repository.save(user);
    }
}
