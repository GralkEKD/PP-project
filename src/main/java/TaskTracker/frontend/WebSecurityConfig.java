package TaskTracker.frontend;

import TaskTracker.businessLogic.services.UserService;
import TaskTracker.businessLogic.services.UserServiceImpl;
import TaskTracker.database.map.UserMapper;
import TaskTracker.database.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/registration", "/css/**", "/icons/**", "/scripts/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling.
                        accessDeniedHandler((request, response, accessDeniedException) ->
                                accessDeniedException.printStackTrace()))
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/tasks")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .csrf((csrf) -> csrf.disable());
        return http.build();
    }

    @Autowired
    UserServiceImpl userService;
    @Bean
    public UserDetailsService userDetailsService(){
        return userService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}