package TaskTracker.businessLogic.requestsHandling;

public class TaskNotFoundException extends RuntimeException {
    private final int taskId;

    public TaskNotFoundException(int taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return ("Task with ID \"" + taskId + "\" not found");
    }
}
