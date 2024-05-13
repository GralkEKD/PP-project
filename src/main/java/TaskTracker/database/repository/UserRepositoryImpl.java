package TaskTracker.database.repository;

import TaskTracker.database.beans.User;
import TaskTracker.database.map.UserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(UserMapper userMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    public Optional<User> getUserByUserLogin(String userLogin) {
        String GET_USER_BY_USER_LOGIN = "SELECT * FROM users WHERE userlogin = :userLogin"; // Mock
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userLogin", userLogin);
        return jdbcTemplate.query(
                GET_USER_BY_USER_LOGIN,
                params,
                userMapper
        ).stream().findFirst();
    }
}
