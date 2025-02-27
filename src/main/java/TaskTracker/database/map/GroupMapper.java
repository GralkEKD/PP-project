package TaskTracker.database.map;


import TaskTracker.database.beans.Group;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Group group = new Group();
        group.setGroupID(resultSet.getLong("groupid"));
        group.setGroupName(resultSet.getString("groupname"));
        group.setGroupPassword(resultSet.getString("grouppassword"));
        return group;
    }
}
