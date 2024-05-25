package TaskTracker.businessLogic.requestsHandling;

public class UserNotFoundException extends RuntimeException {

    private final String userLogin;

    public UserNotFoundException(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String getMessage() {
        return "User with login \"" + userLogin + "\" not found";
    }
}
