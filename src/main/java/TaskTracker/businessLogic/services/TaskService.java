package TaskTracker.businessLogic.services;

import TaskTracker.database.beans.Task;

public interface TaskService {
    Task getTask(int taskId);
}
