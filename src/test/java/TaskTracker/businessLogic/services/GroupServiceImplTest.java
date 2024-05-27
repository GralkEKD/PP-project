package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.GroupNotFoundException;
import TaskTracker.database.beans.Group;
import TaskTracker.database.map.GroupMapper;
import TaskTracker.database.repository.GroupRepository;
import TaskTracker.database.repository.GroupRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Optional;

public class GroupServiceImplTest {

    @Mock
    private final GroupRepository mockRepository = new GroupRepositoryImpl(
            new GroupMapper(),
            new NamedParameterJdbcTemplate(new JdbcTemplate())
    ) {
        @Override
        public Optional<Group> getGroupByGroupId(int id) {
            if (id == 1) {
                return Optional.of(new Group(1, "Test Task", "qwerty"));
            }
            throw new GroupNotFoundException(id);
        }
    };

    @Test
    void givenExistentTaskId_whenGetGroupById_thenReturnGroup() {
        Group expectedGroup = new Group(1, "Test Task", "qwerty");
        Group actualGroup = new GroupServiceImpl(mockRepository).getGroup(1);
        Assertions.assertEquals(expectedGroup, actualGroup);
    }

    @Test
    void givenNonExistentTaskId_whenGetGroupById_thenThrowGroupNotFoundException() {
        Assertions.assertThrows(
                GroupNotFoundException.class,
                () -> new GroupServiceImpl(mockRepository).getGroup(0),
                "Group with id \"0\" not found"
        );
    }
}
