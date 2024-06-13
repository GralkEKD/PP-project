package TaskTracker.businessLogic.requestsHandling.beansExceptions;

public class UserNotFoundException extends EntityNotFoundException {

    private final String userLogin;

    public UserNotFoundException(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String getMessage() {
        return "User with login \"" + userLogin + "\" not found;)";
    }
}
