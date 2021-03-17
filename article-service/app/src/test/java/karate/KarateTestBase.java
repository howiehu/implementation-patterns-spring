package karate;

import dev.huhao.example.realworld.articleservice.Application;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public abstract class KarateTestBase implements InitializingBean {
    @LocalServerPort
    int port;

    @Override
    public void afterPropertiesSet() {
        System.setProperty("local.server.port", String.valueOf(port));
    }

    @BeforeEach
    @FlywayTest
    void init() {
    }
}
