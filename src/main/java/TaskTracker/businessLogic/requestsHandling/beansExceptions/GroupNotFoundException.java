package TaskTracker.businessLogic.requestsHandling.beansExceptions;

public class GroupNotFoundException extends EntityNotFoundException {
    private final int groupId;

    public GroupNotFoundException(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getMessage() {
        return "Group with id \"" + groupId + "\" not found";
    }
}
