package TaskTracker.businessLogic.services;

import TaskTracker.database.beans.User;

public interface UserService {
    User getUser(String userLogin);
    void insertUser(User user);
}
