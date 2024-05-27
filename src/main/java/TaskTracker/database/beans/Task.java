package TaskTracker.database.beans;

import java.sql.Timestamp;
import java.util.Objects;

public final class Task {
    private long taskID;
    private String creatorLogin;
    private long creatorGroupID;
    private String taskName;
    private String taskDescription;
    private long taskPriority;
    private boolean isFinished;
    private Timestamp taskExpiryDate;

    public Task() {
    }

    public Task(
            int taskID,
            String creatorLogin,
            int creatorGroupID,
            String taskName,
            String taskDescription,
            int taskPriority,
            boolean isFinished,
            Timestamp taskExpiryDate
    ) {
        this.taskID = taskID;
        this.creatorLogin = creatorLogin;
        this.creatorGroupID = creatorGroupID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.isFinished = isFinished;
        this.taskExpiryDate = taskExpiryDate;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public long getTaskID() {
        return taskID;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public void setCreatorLogin(String creatorLogin) {
        this.creatorLogin = creatorLogin;
    }

    public long getCreatorGroupID() {
        return creatorGroupID;
    }

    public void setCreatorGroupID(long creatorGroupID) {
        this.creatorGroupID = creatorGroupID;
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

    public long getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(long taskPriority) {
        this.taskPriority = taskPriority;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Timestamp getTaskExpiryDate() {
        return taskExpiryDate;
    }

    public void setTaskExpiryDate(Timestamp taskExpiryDate) {
        this.taskExpiryDate = taskExpiryDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Task) obj;
        return this.taskID == that.taskID &&
                Objects.equals(this.creatorLogin, that.creatorLogin) &&
                this.creatorGroupID == that.creatorGroupID &&
                Objects.equals(this.taskName, that.taskName) &&
                Objects.equals(this.taskDescription, that.taskDescription) &&
                this.taskPriority == that.taskPriority &&
                this.isFinished == that.isFinished &&
                Objects.equals(this.taskExpiryDate, that.taskExpiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskID, creatorLogin, creatorGroupID, taskName, taskDescription, taskPriority, isFinished, taskExpiryDate);
    }

    @Override
    public String toString() {
        return "Task[" +
                "taskID=" + taskID + ", " +
                "creatorLogin=" + creatorLogin + ", " +
                "creatorGroupID=" + creatorGroupID + ", " +
                "taskName=" + taskName + ", " +
                "taskDescription=" + taskDescription + ", " +
                "taskPriority=" + taskPriority + ", " +
                "isFinished=" + isFinished + ", " +
                "taskExpiryDate=" + taskExpiryDate + ']';
    }

}
