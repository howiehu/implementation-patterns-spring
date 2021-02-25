package dev.huhao.example.realworld.articleservice.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 由于只测试从 Repository 到数据库的分层边界集成情况，
// 所以使用 @DataJpaTest 而不是 @SpringBootTest 来减少上下文复杂度，同时利用其所提供的测试工具降低测试成本
@DataJpaTest
public abstract class RepositoryTestBase {

    // 多 Gradle Module 情况下对于没用 @SpringBootApplication 的 Module 需要在测试时现实声明以加载 Spring Boot 上下文
    @SpringBootApplication
    // 在测试中必须显式声明 @Entity 所标记的 JPA 实体所在包路径，否则无法自动注入
    @EntityScan(basePackages = {"dev.huhao.example.realworld.articleservice.model"})
    // 开启 Spring Data JPA 的审计功能
    @EnableJpaAuditing
    static class TestConfiguration {
    }
}
