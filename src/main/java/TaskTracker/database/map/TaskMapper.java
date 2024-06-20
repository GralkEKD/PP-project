package TaskTracker.database.map;

import TaskTracker.database.beans.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskMapper implements RowMapper<Task> {

    public Task mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Task task = new Task();
        task.setTaskId(resultSet.getLong("usertaskid"));
        task.setCreatorLogin(resultSet.getString("creatorlogin"));
        task.setCreatorGroupId(resultSet.getLong("creatorgroupid"));
        task.setTaskName(resultSet.getString("taskname"));
        task.setTaskDescription(resultSet.getString("taskdescription"));
        task.setTaskPriority(resultSet.getLong("taskpriority"));
        task.setIsFinished(resultSet.getBoolean("taskstatus"));
        if(resultSet.getTimestamp("taskdeadline")!=null)
            task.setTaskExpiryDate(resultSet.getTimestamp("taskdeadline").toLocalDateTime());
        return task;
    }
}