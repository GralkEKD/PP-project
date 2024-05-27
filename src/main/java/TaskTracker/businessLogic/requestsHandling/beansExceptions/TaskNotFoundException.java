package TaskTracker.businessLogic.requestsHandling.beansExceptions;

public class TaskNotFoundException extends EntityNotFoundException {
    private final int taskId;

    public TaskNotFoundException(int taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return ("Task with ID \"" + taskId + "\" not found");
    }
}
