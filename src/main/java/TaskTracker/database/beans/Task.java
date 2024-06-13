package TaskTracker.database.beans;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Task {
    private Long taskId;
    private String creatorLogin;
    private Long creatorGroupId;
    private String taskName;
    private String taskDescription;
    private Long taskPriority;
    private Boolean isFinished;
    private LocalDateTime taskExpiryDate;

    public Task() {
    }

    public Task(
            Long taskId,
            String creatorLogin,
            Long creatorGroupId,
            String taskName,
            String taskDescription,
            Long taskPriority,
            Boolean isFinished,
            LocalDateTime taskExpiryDate
    ) {
        this.taskId = taskId;
        this.creatorLogin = creatorLogin;
        this.creatorGroupId = creatorGroupId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.isFinished = isFinished;
        this.taskExpiryDate = taskExpiryDate;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public void setCreatorLogin(String creatorLogin) {
        this.creatorLogin = creatorLogin;
    }

    public Long getCreatorGroupId() {
        return creatorGroupId;
    }

    public void setCreatorGroupId(Long creatorGroupId) {
        this.creatorGroupId = creatorGroupId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Long getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Long taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    public LocalDateTime getTaskExpiryDate() {
        return taskExpiryDate;
    }

    public void setTaskExpiryDate(LocalDateTime taskExpiryDate) {
        this.taskExpiryDate = taskExpiryDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Task) obj;
        return Objects.equals(this.taskId, that.taskId) &&
                Objects.equals(this.creatorLogin, that.creatorLogin) &&
                Objects.equals(this.creatorGroupId, that.creatorGroupId) &&
                Objects.equals(this.taskName, that.taskName) &&
                Objects.equals(this.taskDescription, that.taskDescription) &&
                Objects.equals(this.taskPriority, that.taskPriority) &&
                Objects.equals(this.isFinished, that.isFinished) &&
                Objects.equals(this.taskExpiryDate, that.taskExpiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, creatorLogin, creatorGroupId, taskName, taskDescription, taskPriority, isFinished, taskExpiryDate);
    }

    @Override
    public String toString() {
        return "Task[" +
                "taskID=" + taskId + ", " +
                "creatorLogin=" + creatorLogin + ", " +
                "creatorGroupID=" + creatorGroupId + ", " +
                "taskName=" + taskName + ", " +
                "taskDescription=" + taskDescription + ", " +
                "taskPriority=" + taskPriority + ", " +
                "isFinished=" + isFinished + ", " +
                "taskExpiryDate=" + taskExpiryDate + ']';
    }

}
