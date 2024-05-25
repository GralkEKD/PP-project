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

import java.util.Optional;

@Repository
@PropertySource("queries.properties")
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger userRepositoryLogger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Value("${getUserByLogin}")
    String getUserByLogin;
    @Value("${insertUser}")
    String insertUser;

    public UserRepositoryImpl(UserMapper userMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUserByUserLogin(String userLogin) {
        var params = new MapSqlParameterSource();
        params.addValue("userLogin", userLogin);
        return jdbcTemplate.query(
                getUserByLogin,
                params,
                userMapper
        ).stream().findFirst();
    }

    @Override
    public void insertUser(User user) {
        MapSqlParameterSource[] params = new MapSqlParameterSource[] {
                new MapSqlParameterSource(),
                new MapSqlParameterSource()
        };
        params[0].addValue("userLogin", user.getUserLogin())
                .addValue("userName", user.getUserName())
                .addValue("userPassword", user.getUserPassword());
        params[1].addValue("userLogin", user.getUserLogin());
        // Добавить логгер (update возвращает int - количество задействованных строк)
        int[] affectedRows = jdbcTemplate.batchUpdate(
                insertUser,
                params
        );
        userRepositoryLogger.info("INSERT INTO users: " + affectedRows[0] + " rows affected");
        userRepositoryLogger.info("INSERT INTO userandgroup: " + affectedRows[1] + " rows affected");
    }
}
