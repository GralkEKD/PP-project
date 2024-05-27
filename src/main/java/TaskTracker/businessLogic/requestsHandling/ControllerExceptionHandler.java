package TaskTracker.businessLogic.requestsHandling;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ErrorInfo processException(EntityNotFoundException e) {
        logger.error("Unexpected Error, ", e);
        return new ErrorInfo(e, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    String entityNotFoundException(EntityNotFoundException e) {
        return e.getMessage();
    }
}
