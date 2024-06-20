package TaskTracker.database.repository;

import TaskTracker.database.beans.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> getGroupByGroupId(Long id);
}
