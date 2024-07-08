package TaskTracker.database.repository;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.UserNotFoundException;
import TaskTracker.database.beans.Task;
import TaskTracker.database.beans.User;
import TaskTracker.database.map.TaskMapper;
import TaskTracker.database.map.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@PropertySource(value="queries.properties")
public class TaskRepositoryImpl implements TaskRepository{

    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TaskRepositoryImpl.class);

    @Value("${getTaskById}")
    String getTaskById;
    @Value("${getGivenUserTasks}")
    String getTasksOfUser;
    @Value("${deleteTaskById}")
    String deleteTaskById;
    @Value("${updateTask}")
    String updateTask;
    @Value("${addTask}")
    String addTask;
    @Value("${getUserByLogin}")
    String getUserByLogin;

    public TaskRepositoryImpl(TaskMapper taskMapper, UserMapper userMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.taskMapper = taskMapper;
        this.userMapper = userMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        var params = new MapSqlParameterSource();
        params.addValue("userTaskId", taskId);
        logger.info("Executing SQL " + getTaskById);
        return jdbcTemplate.query(
                getTaskById,
                params,
                taskMapper
        ).stream().findFirst();
    }

    @Override
    public List<Task> getTasksOfUser(String userName) {
        var params = new MapSqlParameterSource();
        params.addValue("userName", userName);
        Optional<User> userCheck = jdbcTemplate.query(
                getUserByLogin,
                params,
                userMapper
        ).stream().findFirst();
        if (userCheck.isPresent()) {
            logger.info("Executing SQL " + getTasksOfUser);
            return jdbcTemplate.query(
                    getTasksOfUser,
                    params,
                    taskMapper
            );
        }
        throw new UserNotFoundException(userName);
    }

    @Override
    public void sex(Long id) {
        var params = new MapSqlParameterSource();
        params.addValue("taskId", id);
        logger.info("Executing SQL " + deleteTaskById);
        int affectedRows = jdbcTemplate.update(
                deleteTaskById,
                params
        );
        logger.info(affectedRows + " rows affected");
    }

    @Override
    public void updateTask(Task task) {
        var params = new MapSqlParameterSource();
        params.addValue("newTaskName", task.getTaskName());
        params.addValue("newTaskDescription", task.getTaskDescription());
        params.addValue("newTaskPriority", task.getTaskPriority());
        params.addValue("newTaskStatus", task.getIsFinished());
        params.addValue("newTaskDeadline", task.getTaskExpiryDate());
        params.addValue("taskId", task.getTaskId());
        logger.info("Executing SQL " + updateTask);
        int affectedRows = jdbcTemplate.update(
                updateTask,
                params
        );
        logger.info(affectedRows + " rows affected");
    }

    @Override
    public void addTask(Task task) {
        var params = new MapSqlParameterSource();
        params.addValue("creatorLogin", task.getCreatorLogin());
        params.addValue("creatorGroupId", task.getCreatorGroupId());
        params.addValue("taskName", task.getTaskName());
        params.addValue("taskDescription", task.getTaskDescription());
        params.addValue("taskPriority", task.getTaskPriority());
        params.addValue("taskStatus", task.getIsFinished());
        params.addValue("taskDeadline", task.getTaskExpiryDate());
        logger.info("Executing SQL " + addTask);
        int affectedRows = jdbcTemplate.update(
                addTask,
                params
        );
        logger.info(affectedRows + " rows affected");
    }
}
