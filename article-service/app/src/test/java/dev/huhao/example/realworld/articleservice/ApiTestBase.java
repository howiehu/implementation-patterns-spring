package dev.huhao.example.realworld.articleservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// 启用完整服务并提供随机端口
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class ApiTestBase {

    // 注入随机端口
    @LocalServerPort
    private int port;

    // RestAssured 使用随机端口运行 API 功能测试
    @BeforeEach
    void init() {
        RestAssured.port = port;
    }
}
