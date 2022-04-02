package training_manager.demo.service.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import training_manager.demo.dto.UserDTO;
import training_manager.demo.enums.TrainingTypeEnum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext
class UserServiceTest {

//              ,---.
//             '   .-'   ,---.  ,--.--. ,--.--. ,--. ,--.
//             `.  `-.  | .-. | |  .--' |  .--'  \  '  /
//             .-'    | ' '-' ' |  |    |  |      \   '
//             `-----'   `---'  `--'    `--'    .-'  /
//                                              `---'

    @Mock
    private UserService userService;
    private UserDTO userDTO = new UserDTO(111l, "test", 50, TrainingTypeEnum.AMATEUR);

    @BeforeEach
    void init() {
        Mockito.lenient().when(userService.findById(111L)).thenReturn(userDTO);
        Mockito.lenient().when(userService.findByNickname("test")).thenReturn(userDTO);
        Mockito.lenient().when(userService.findAllUser()).thenReturn(List.of(userDTO));
        Mockito.lenient().when(userService.existsUserByNickname("test")).thenReturn(true);

    }

    @Test
    void findById() {
        UserDTO byId = userService.findById(userDTO.getId());
        assertEquals(userDTO, byId);
    }

    @Test
    void findByNickname() {
        assertEquals(userDTO, userService.findByNickname("test"));
    }

    @Test
    void findAllUser() {
        assertEquals(List.of(userDTO), userService.findAllUser());
    }

    @Test
    void existsUserByNickname() {
        assertTrue(userService.existsUserByNickname("test"));
    }

    @Test
    void nonExistsUserByNickname() {
        assertFalse(userService.existsUserByNickname("some"));
    }
}