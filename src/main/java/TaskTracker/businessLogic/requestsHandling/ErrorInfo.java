package TaskTracker.businessLogic.requestsHandling;

public record ErrorInfo(
        RuntimeException exception,
        String message
) {}
