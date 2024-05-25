package TaskTracker.database.repository;

import TaskTracker.database.beans.Task;
import TaskTracker.database.map.TaskMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@PropertySource(value="queries.properties")
public class TaskRepositoryImpl implements TaskRepository{

    private final TaskMapper taskMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${getTaskById}")
    String getTaskById;

    public TaskRepositoryImpl(TaskMapper taskMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.taskMapper = taskMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Task> getTaskById(int taskId) {
        var params = new MapSqlParameterSource();
        params.addValue("usertaskid", taskId);
        return jdbcTemplate.query(
                getTaskById,
                params,
                taskMapper
        ).stream().findFirst();
    }
}
