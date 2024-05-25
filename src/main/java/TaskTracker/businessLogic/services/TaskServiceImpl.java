package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.TaskNotFoundException;
import TaskTracker.database.beans.Task;
import TaskTracker.database.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(int taskId) {
        return taskRepository.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
