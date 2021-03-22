package dev.huhao.example.realworld.articleservice;

import org.flywaydb.core.Flyway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// 仅用于在测试时通过 RESTful API 提供测试专用功能
@RestController
@RequestMapping("/karate-tests")
public class KarateTestController {

    private final Flyway flyway;

    public KarateTestController(Flyway flyway) {

        this.flyway = flyway;
    }

    @PostMapping("/db-restoration")
    @ResponseStatus(HttpStatus.OK)
    public void resetDb() {
        flyway.clean();
        flyway.migrate();
    }
}
