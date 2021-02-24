package dev.huhao.example.articleservice.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public abstract class RepositoryTestBase {

    @SpringBootApplication
    @EntityScan(basePackages = {"dev.huhao.example.articleservice.model"})
    static class TestConfiguration {
    }
}
