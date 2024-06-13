package TaskTracker.database.repository;

import TaskTracker.database.beans.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> getTaskById(int taskId);

    List<Task> getTasksOfUser(String userName);

    void deleteTaskById(Long id);

    void updateTask(Task task);

    void addTask(Task task);
}
