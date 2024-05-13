package TaskTracker.businessLogic.requestsHandling;

public record ErrorInfo(
        Exception exception,
        String message
) {}
