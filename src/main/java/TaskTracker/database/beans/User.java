package TaskTracker.database.beans;

import java.util.Objects;

public class User implements java.io.Serializable{
    private String userName;
    private String userPassword;

    public User() {}

    public User(
            String userName,
            String userPassword
    ) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.userName, that.userName) &&
                Objects.equals(this.userPassword, that.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPassword);
    }

    @Override
    public String toString() {
        return "User[" +
                "userName=" + userName + ", " +
                "userPassword=" + userPassword + ']';
    }
}
