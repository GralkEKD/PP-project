package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.TaskNotFoundException;
import TaskTracker.businessLogic.requestsHandling.beansExceptions.UserNotFoundException;
import TaskTracker.database.beans.Task;
import TaskTracker.database.map.TaskMapper;
import TaskTracker.database.map.UserMapper;
import TaskTracker.database.repository.TaskRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskServiceImplTest {

    @Mock
    private final TaskRepositoryImpl mockTaskRepository = new TaskRepositoryImpl(
            new TaskMapper(),
            new UserMapper(),
            new NamedParameterJdbcTemplate(new JdbcTemplate())
    ) {
        @Override
        public Optional<Task> getTaskById(Long taskId) {
            if (taskId == 1L)
                return Optional.of(new Task(
                        1L,
                        "Creator",
                        1L,
                        "Name",
                        "Desc",
                        0L,
                        false,
                        LocalDateTime.MAX
                ));
            return Optional.empty();
        }

        @Override
        public List<Task> getTasksOfUser(String userName) {
            if (userName.equals("Creator"))
                return List.of(
                        new Task(
                        1L,
                        "Creator",
                        1L,
                        "Name1",
                        "Desc1",
                        0L,
                        false,
                        LocalDateTime.MAX
                        ),
                        new Task(
                                2L,
                                "Creator",
                                1L,
                                "Name2",
                                "Desc2",
                                1L,
                                false,
                                LocalDateTime.MAX
                        )
                );
            throw new UserNotFoundException(userName);
        }
    };

    @Test
    public void givenExistingTaskId_whenGetTaskById_thenReturnOptionalOfTask() {
        Task expectedTask = new Task(
                1L,
                "Creator",
                1L,
                "Name",
                "Desc",
                0L,
                false,
                LocalDateTime.MAX
        );
        Task actualTask = new TaskServiceImpl(mockTaskRepository).getTask(1L);
        Assertions.assertEquals(expectedTask, actualTask);
    }

    @Test
    public void givenNonExistingTaskId_whenGetTaskById_thenThrowTaskNotFoundException() {
        Assertions.assertThrows(
                TaskNotFoundException.class,
                () -> new TaskServiceImpl(mockTaskRepository).getTask(2L)
        );
    }

    @Test
    public void givenExistingUserName_whenGetTasksOfUser_thenReturnListOfTasks() {
        List<Task> expectedTasks = List.of(
                new Task(
                        1L,
                        "Creator",
                        1L,
                        "Name1",
                        "Desc1",
                        0L,
                        false,
                        LocalDateTime.MAX
                ),
                new Task(
                        2L,
                        "Creator",
                        1L,
                        "Name2",
                        "Desc2",
                        1L,
                        false,
                        LocalDateTime.MAX
                )
        );
        List<Task> actualTasks = new TaskServiceImpl(mockTaskRepository).getTasksOfUser("Creator");
        Assertions.assertEquals(expectedTasks, actualTasks);
    }

    @Test
    public void givenNonExistingUserName_whenGetTasksOfUser_thenReturnEmptyList() {
        Assertions.assertThrows(
                UserNotFoundException.class,
                () -> new TaskServiceImpl(mockTaskRepository).getTasksOfUser("FakeCreator")
        );
    }
}
