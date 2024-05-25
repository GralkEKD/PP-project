package TaskTracker.database.repository;

import TaskTracker.database.beans.Task;

import java.util.Optional;

public interface TaskRepository {

    Optional<Task> getTaskById(int taskId);
}
