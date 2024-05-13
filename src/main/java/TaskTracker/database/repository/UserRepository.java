package TaskTracker.database.repository;

import TaskTracker.database.beans.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByUserLogin(String userLogin);
}
