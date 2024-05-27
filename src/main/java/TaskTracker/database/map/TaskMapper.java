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
        task.setTaskID(resultSet.getLong("taskid"));
        task.setCreatorLogin(resultSet.getString("creatorLogin"));
        task.setCreatorGroupID(resultSet.getLong("creatorgroupid"));
        task.setTaskName(resultSet.getString("taskname"));
        task.setTaskDescription(resultSet.getString("taskdescription"));
        task.setTaskPriority(resultSet.getLong("taskpriority"));
        task.setFinished(resultSet.getBoolean("taskstatus"));
        task.setTaskExpiryDate(resultSet.getTimestamp("taskexpirydate"));
        return task;
    }
}
