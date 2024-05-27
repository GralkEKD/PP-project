package TaskTracker.businessLogic.requestsHandling;

import TaskTracker.businessLogic.services.UserService;
import TaskTracker.database.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userLogin}", produces="application/json")
    public User getUser(@PathVariable("userLogin") String userLogin) {
        User user = userService.getUser(userLogin);
        logger.info("Sending object: " + user);
        return user;
    }

    @PostMapping(value="/sign_up", consumes="application/json", produces="application/json")
    public User insertUser(@RequestBody User user) {
        logger.info("Received object: " + user);
        return userService.insertUser(user);
    }
}
