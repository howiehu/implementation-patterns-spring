package dev.huhao.example.realworld.articleservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ArticleApiTest extends ApiTestBase {

    @Nested
    @DisplayName("GET /articles/{slug}")
    class getArticle {

        @Test
        void should_return_404_with_message_when_not_found_by_slug() {
            given()
                    .when()
                    .get("/articles/fake-slug")
                    .then()
                    .statusCode(404)
                    .body("message", is("cannot find the article with slug fake-slug"));
        }
    }
}
