package dev.huhao.example.realworld.articleservice;

import io.restassured.RestAssured;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// 启用完整服务并提供随机端口
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
/* 使用 flyway-test-extensions 的配置要求，由于覆盖了默认的 @TestExecutionListeners 配置，
   所以需要显式加入 DependencyInjectionTestExecutionListener.class，默认配置请看 @TestExecutionListeners 的实现*/
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public abstract class ApiTestBase {

    // 注入随机端口
    @LocalServerPort
    private int port;

    // RestAssured 使用随机端口运行 API 功能测试
    @BeforeEach
    // 使用 https://github.com/flyway/flyway-test-extensions 来实现测试时重置数据库
    @FlywayTest
    void init() {
        RestAssured.port = port;
    }
}
