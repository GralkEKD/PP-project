package TaskTracker.database.beans;

import java.util.Objects;

public class Group implements java.io.Serializable{
    private int groupID;
    private String groupName;
    private String groupPassword;

    public Group() {}

    public Group(
            int groupID,
            String groupName,
            String groupPassword
    ) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.groupPassword = groupPassword;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupPassword(String groupPassword) {
        this.groupPassword = groupPassword;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupPassword() {
        return groupPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Group) obj;
        return this.groupID == that.groupID &&
                Objects.equals(this.groupName, that.groupName) &&
                Objects.equals(this.groupPassword, that.groupPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupID, groupName, groupPassword);
    }

    @Override
    public String toString() {
        return "Group[" +
                "groupID=" + groupID + ", " +
                "groupName=" + groupName + ", " +
                "groupPassword=" + groupPassword + ']';
    }

}
