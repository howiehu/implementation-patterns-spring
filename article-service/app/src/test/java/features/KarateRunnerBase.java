package features;

import dev.huhao.example.realworld.articleservice.Application;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

// 启用完整服务并提供随机端口，指定 Application 作为 Spring Boot 上下文的入口
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 使用 https://github.com/flyway/flyway-test-extensions 来实现每个测试类重置数据库
// 测试过程中的数据库重置依靠自定义 Karate 的 Feature 显式调用便于优化测试设计和降低 API 功能测试的数据重置成本
@FlywayTest
/* 使用 flyway-test-extensions 的配置要求，由于覆盖了默认的 @TestExecutionListeners 配置，
   所以需要显式加入 DependencyInjectionTestExecutionListener.class，默认配置请看 @TestExecutionListeners 的实现*/
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public abstract class KarateRunnerBase implements InitializingBean {

    // 注入随机端口
    @LocalServerPort
    int port;

    @Override
    public void afterPropertiesSet() {
        // 通过系统属性将随机端口提供给 Karate 调用
        System.setProperty("local.server.port", String.valueOf(port));
    }
}
