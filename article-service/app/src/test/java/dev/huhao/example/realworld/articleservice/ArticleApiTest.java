package dev.huhao.example.realworld.articleservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ArticleApiTest extends ApiTestBase {

    @Nested
    @DisplayName("POST /articles")
    class createArticle {

        @Test
        void should_create_article() {
            var authorId = UUID.randomUUID().toString();

            given()
                    .accept(JSON)
                    .contentType(JSON)
                    .body(Map.of(
                            "title", "Fake Title",
                            "description", "Description",
                            "body", "Something",
                            "authorId", authorId
                    ))
                    .when()
                    .post("/articles")
                    .then()
                    .statusCode(201)
                    .contentType(JSON)
                    .body(
                            "slug", is("fake-title"),
                            "title", is("Fake Title"),
                            "description", is("Description"),
                            "body", is("Something"),
                            "authorId", is(authorId),
                            "createdAt", notNullValue(),
                            "updatedAt", notNullValue()
                    );

            given()
                    .accept(JSON)
                    .when()
                    .get("/articles/fake-title")
                    .then()
                    .statusCode(200)
                    .contentType(JSON)
                    .body(
                            "slug", is("fake-title"),
                            "title", is("Fake Title"),
                            "description", is("Description"),
                            "body", is("Something"),
                            "authorId", is(authorId),
                            "createdAt", notNullValue(),
                            "updatedAt", notNullValue()
                    );
        }

        @Test
        void should_409_conflict_with_message_when_slug_existed() {
            var authorId = UUID.randomUUID().toString();

            given()
                    .accept(JSON)
                    .contentType(JSON)
                    .body(Map.of(
                            "title", "Fake Title",
                            "description", "Description",
                            "body", "Something",
                            "authorId", authorId
                    ))
                    .when()
                    .post("/articles");

            given()
                    .accept(JSON)
                    .contentType(JSON)
                    .body(Map.of(
                            "title", "Fake Title",
                            "description", "Description",
                            "body", "Something",
                            "authorId", authorId
                    ))
                    .when()
                    .post("/articles")
                    .then()
                    .statusCode(409)
                    .contentType(JSON)
                    .body("message", is("the article with slug fake-title already exists"));
        }
    }

    @Nested
    @DisplayName("GET /articles/{slug}")
    class getArticle {

        @Test
        void should_return_404_with_message_when_not_found_by_slug() {
            given()
                    .accept(JSON)
                    .when()
                    .get("/articles/fake-slug")
                    .then()
                    .statusCode(404)
                    .contentType(JSON)
                    .body("message", is("cannot find the article with slug fake-slug"));
        }
    }
}
