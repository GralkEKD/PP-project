package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.UserNotFoundException;
import TaskTracker.database.beans.User;
import TaskTracker.database.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userLogin) {
        return userRepository.getUserByUserLogin(userLogin)
                .orElseThrow(() -> new UserNotFoundException(userLogin));
    }
}
