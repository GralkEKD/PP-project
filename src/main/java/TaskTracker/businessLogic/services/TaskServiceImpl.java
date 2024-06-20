package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.TaskNotFoundException;
import TaskTracker.database.beans.Task;
import TaskTracker.database.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(Long taskId) {
        return taskRepository.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Override
    public List<Task> getTasksOfUser(String userName) {
        return taskRepository.getTasksOfUser(userName);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteTaskById(id);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }

    @Override
    public void addTask(Task task) {
        taskRepository.addTask(task);
    }
}
