package TaskTracker.database.map;


import TaskTracker.database.beans.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Group group = new Group();
        group.setGroupID(resultSet.getInt("groupid"));
        group.setGroupName(resultSet.getString("groupname"));
        group.setGroupPassword(resultSet.getString("grouppassword"));
        return group;
    }
}
