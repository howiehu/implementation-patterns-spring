package dev.huhao.example.realworld.articleservice.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class ServiceTestBase {

    // 多 Gradle Module 情况下对于没用 @SpringBootApplication 的 Module 需要在测试时现实声明以加载 Spring Boot 上下文
    // 由于对 Service 层进行单元测试 Stub 掉了 Repository，所以需要禁用 Spring Data Jpa 的相关自动配置功能
    @SpringBootApplication(exclude = {
            DataSourceAutoConfiguration.class,
            DataSourceTransactionManagerAutoConfiguration.class,
            HibernateJpaAutoConfiguration.class
    })
    static class TestConfiguration {
    }
}
