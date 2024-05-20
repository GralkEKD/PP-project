package TaskTracker.database.map;


import TaskTracker.database.beans.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new Group(
                resultSet.getInt("groupid"),
                resultSet.getString("groupname"),
                resultSet.getString("grouppassword")
        );
    }
}
