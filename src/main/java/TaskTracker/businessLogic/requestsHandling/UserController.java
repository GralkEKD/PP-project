package TaskTracker.businessLogic.requestsHandling;

import TaskTracker.businessLogic.services.UserService;
import TaskTracker.database.beans.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "localhost")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(String userLogin) {
        return userService.getUser(userLogin);
    }
}
