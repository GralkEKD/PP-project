package TaskTracker.database.repository;

import TaskTracker.database.beans.User;
import TaskTracker.database.map.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
@PropertySource("queries.properties")
public class UserRepositoryImpl implements UserRepository{

    private final UserMapper userMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger userRepositoryLogger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Value("${getUserByLogin}")
    String getUserByLogin;
    @Value("${insertUser}")
    String insertUser;
    @Value("${addUserToGroup}")
    String addUserToGroup;

    public UserRepositoryImpl(UserMapper userMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUserByUserLogin(String userLogin) {
        var params = new MapSqlParameterSource();
        params.addValue("userName", userLogin);
        return jdbcTemplate.query(
                getUserByLogin,
                params,
                userMapper
        ).stream().findFirst();
    }

    @Override
    public User insertUser(User user) {
        int affectedRows;
        var params = new MapSqlParameterSource();
        params.addValue("userName", user.getUserName());
        params.addValue("userPassword", user.getUserPassword());
        params.addValue("groupId", 0);

        userRepositoryLogger.info("Executing SQL: " + insertUser);
        affectedRows = jdbcTemplate.update(insertUser, params);
        userRepositoryLogger.info("INSERT INTO users: " + affectedRows + " rows affected");

        userRepositoryLogger.info("Executing SQL: " + addUserToGroup);
        affectedRows = jdbcTemplate.update(addUserToGroup, params);
        userRepositoryLogger.info("INSERT INTO userandgroup: " + affectedRows + " rows affected");

        return user;
    }
}
