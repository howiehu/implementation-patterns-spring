package dev.huhao.example.realworld.articleservice.protocol.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest
public abstract class ControllerTestBase {

    @SpringBootApplication
    static class TestConfiguration {
    }
}
