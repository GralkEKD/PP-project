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

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UserRepositoryImpl userRepository;

    private static final Logger userServiceLogger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userName) {
        Optional<User> user = userRepository.getUserByUserLogin(userName);
        userServiceLogger.info("Received user: " + user);
        return user.orElse(null);
    }

    @Override
    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        return Optional.of(org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUserName())
                .password(user.getUserPassword())
                .authorities("USER")
                .build()).orElseThrow(() -> new UserNotFoundException(username));
    }
}