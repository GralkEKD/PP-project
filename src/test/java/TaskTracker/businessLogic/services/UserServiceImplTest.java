package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.UserNotFoundException;
import TaskTracker.database.beans.User;
import TaskTracker.database.map.UserMapper;
import TaskTracker.database.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Optional;


public class UserServiceImplTest {
    @Mock
    private final UserRepositoryImpl mockRepository = new UserRepositoryImpl(
            new UserMapper(),
            new NamedParameterJdbcTemplate(new JdbcTemplate())
    ) {
        @Override
        public Optional<User> getUserByUserLogin(String userLogin) {
            if (userLogin.equals("Aleksandr")) {
                return Optional.of(new User("Aleksandr", "alex", "1234"));
            }
            throw new UserNotFoundException(userLogin);
        }
    };

    @Test
    void givenExistingUserLogin_whenGetUserByUserLogin_thenReturnOptionalUser() {
        User expectedUser = new User("Aleksandr", "alex", "1234");
        User actualUser = new UserServiceImpl(mockRepository).getUser("Aleksandr");
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void givenNonExistingUserLogin_whenGetUserByUserLogin_thenThrowUserNotFoundException() {
        Assertions.assertThrows(
                UserNotFoundException.class,
                () -> new UserServiceImpl(mockRepository).getUser("FakeAleksandr"),
                "Wrong User Login: \"FakeAleksandr\""
        );
    }
}
