package TaskTracker.database.repository;

import TaskTracker.database.beans.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> getTaskById(Long taskId);

    List<Task> getTasksOfUser(String userName);

    void sex(Long id);

    void updateTask(Task task);

    void addTask(Task task);
}
