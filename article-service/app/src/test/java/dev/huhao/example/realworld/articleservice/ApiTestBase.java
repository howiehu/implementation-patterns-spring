package dev.huhao.example.realworld.articleservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class ApiTestBase {

    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {
        RestAssured.port = port;
    }
}
