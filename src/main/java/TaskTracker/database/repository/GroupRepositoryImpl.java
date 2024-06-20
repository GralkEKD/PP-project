package TaskTracker.database.repository;

import TaskTracker.database.beans.Group;
import TaskTracker.database.map.GroupMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@PropertySource("queries.properties")
public class GroupRepositoryImpl implements GroupRepository {

    private final GroupMapper groupMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${getGroupById}")
    String getGroupById;

    public GroupRepositoryImpl(GroupMapper groupMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.groupMapper = groupMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Group> getGroupByGroupId(Long id) {
        var parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("groupid", id);
        return jdbcTemplate.query(
                getGroupById,
                parameterSource,
                groupMapper
        ).stream().findFirst();
    }
}
