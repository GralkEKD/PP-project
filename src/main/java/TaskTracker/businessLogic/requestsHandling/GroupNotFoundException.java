package TaskTracker.businessLogic.requestsHandling;

public class GroupNotFoundException extends RuntimeException {
    private final int groupId;

    public GroupNotFoundException(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getMessage() {
        return "Group with id \"" + groupId + "\" not found";
    }
}
