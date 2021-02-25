package dev.huhao.example.realworld.articleservice.api.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

// 由于 SUT 为 Controller 层，所以需要 Fake Http Request 以及 Stub Service，来隔离和验证组件边界
// 所以使用 @WebMvcTest 而不是 @SpringBootTest 来减少上下文复杂度，同时利用其所提供的测试工具降低测试成本
@WebMvcTest
public abstract class ControllerTestBase {

    // 多 Gradle Module 情况下对于没用 @SpringBootApplication 的 Module 需要在测试时现实声明以加载 Spring Boot 上下文
    @SpringBootApplication
    static class TestConfiguration {
    }
}
