package TaskTracker.database.map;

import TaskTracker.database.beans.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new User(
                resultSet.getString("username"),
                resultSet.getString("userlogin"),
                resultSet.getString("userpassword")
        );
    }
}
