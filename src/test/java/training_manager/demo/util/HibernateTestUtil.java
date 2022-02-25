package training_manager.demo.util;

import lombok.experimental.UtilityClass;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class HibernateTestUtil {

    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:13");

    static {
        postgres.start();
    }

}
