package TaskTracker.database.beans;

public record Group(
        int groupID,
        String groupName,
        String groupPassword
) {
}
