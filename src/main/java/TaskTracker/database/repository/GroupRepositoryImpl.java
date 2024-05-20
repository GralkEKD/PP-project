package TaskTracker.database.repository;

import TaskTracker.database.Query;
import TaskTracker.database.beans.Group;
import TaskTracker.database.map.GroupMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    private final GroupMapper groupMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public GroupRepositoryImpl(GroupMapper groupMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.groupMapper = groupMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Group> getGroupByGroupId(int id) {
        var parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("groupid", id);
        return jdbcTemplate.query(
                Query.GET_GROUP_BY_GROUP_ID.toString(),
                parameterSource,
                groupMapper
        ).stream().findFirst();
    }
}
