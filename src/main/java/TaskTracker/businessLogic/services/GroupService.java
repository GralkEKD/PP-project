package TaskTracker.businessLogic.services;

import TaskTracker.database.beans.Group;

public interface GroupService {
    Group getGroup(Long id);
}
