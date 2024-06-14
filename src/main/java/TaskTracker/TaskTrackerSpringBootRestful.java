package TaskTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"TaskTracker.database"})
@ComponentScan({"TaskTracker.businessLogic"})
@ComponentScan({"TaskTracker.frontend"})
@SpringBootApplication
public class TaskTrackerSpringBootRestful {
    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerSpringBootRestful.class);
    }
}
