package TaskTracker.businessLogic.requestsHandling.beansExceptions;

public class TaskNotFoundException extends EntityNotFoundException {
    private final Long taskId;

    public TaskNotFoundException(Long taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return ("Task with ID \"" + taskId + "\" not found");
    }
}
