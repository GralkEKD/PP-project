package TaskTracker.businessLogic.requestsHandling;

import TaskTracker.businessLogic.services.UserService;
import TaskTracker.database.beans.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userLogin}")
    public User getUser(@PathVariable("userLogin") String userLogin) {
        return userService.getUser(userLogin);
    }

    @PostMapping
    public void insertUser(User user) {
        userService.insertUser(user);
    }
}
