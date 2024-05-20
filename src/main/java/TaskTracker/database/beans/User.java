package TaskTracker.database.beans;

import java.util.Objects;

public class User {
    private String userName;
    private String userLogin;
    private String userPassword;

    public User() {}

    public User(
            String userName,
            String userLogin,
            String userPassword
    ) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLogin() {
        return userLogin;
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
                Objects.equals(this.userLogin, that.userLogin) &&
                Objects.equals(this.userPassword, that.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userLogin, userPassword);
    }

    @Override
    public String toString() {
        return "User[" +
                "userName=" + userName + ", " +
                "userLogin=" + userLogin + ", " +
                "userPassword=" + userPassword + ']';
    }
}
