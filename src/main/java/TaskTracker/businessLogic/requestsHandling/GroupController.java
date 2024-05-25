package TaskTracker.businessLogic.requestsHandling;

import TaskTracker.businessLogic.services.GroupService;
import TaskTracker.database.beans.Group;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public Group getGroup(int id) {
        return groupService.getGroup(id);
    }
}
