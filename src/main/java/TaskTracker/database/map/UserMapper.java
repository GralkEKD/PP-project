package TaskTracker.database.map;

import TaskTracker.database.beans.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setUserLogin(resultSet.getString("userlogin"));
        user.setUserName(resultSet.getString("username"));
        user.setUserPassword(resultSet.getString("userpassword"));
        return user;
    }
}
