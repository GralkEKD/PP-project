package TaskTracker.businessLogic.services;

import TaskTracker.businessLogic.requestsHandling.beansExceptions.GroupNotFoundException;
import TaskTracker.database.beans.Group;
import TaskTracker.database.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group getGroup(int id) {
        return groupRepository.getGroupByGroupId(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }
}
