package TaskTracker.businessLogic.services;

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
                return Optional.of(new User("Aleksandr", "1234"));
            }
            return Optional.empty();
        }
    };

    @Test
    void givenExistingUserLogin_whenGetUserByUserLogin_thenReturnOptionalUser() {
        User expectedUser = new User("Aleksandr", "1234");
        User actualUser = new UserServiceImpl(mockRepository).getUser("Aleksandr");
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void givenNonExistingUserLogin_whenGetUserByUserLogin_thenReturnNull() {
        User actualUser = new UserServiceImpl(mockRepository).getUser("FakeAleksandr");
        Assertions.assertNull(actualUser);
    }
}
