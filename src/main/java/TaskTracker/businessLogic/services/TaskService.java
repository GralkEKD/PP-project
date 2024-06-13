package TaskTracker.businessLogic.services;

import TaskTracker.database.beans.Task;

import java.util.List;

public interface TaskService {
    Task getTask(Long taskId);

    List<Task> getTasksOfUser(String userName);

    void deleteTask(Long id);

    void updateTask(Task task);

    void addTask(Task task);
}
