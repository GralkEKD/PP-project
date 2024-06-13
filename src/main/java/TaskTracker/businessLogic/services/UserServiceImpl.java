package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.UserNotFoundException;
import TaskTracker.database.beans.User;
import TaskTracker.database.repository.UserRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UserRepositoryImpl userRepository;

    private static final Logger userServiceLogger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userLogin) {
        User user = userRepository.getUserByUserLogin(userLogin)
                .orElseThrow(() -> new UserNotFoundException(userLogin));
        userServiceLogger.info("Received user: " + user);
        return user;
    }

    @Override
    public User insertUser(User user) {
        return userRepository.insertUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(getUser(username).getUserName())
                .password(getUser(username).getUserPassword())
                .authorities("USER")
                .build();
    }
}
