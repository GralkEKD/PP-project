package TaskTracker.businessLogic.requestsHandling.beansExceptions;

public class EntityNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Unexpected error";
    }
}
