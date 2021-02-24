package dev.huhao.example.realworld.articleservice.protocol.controller;

import dev.huhao.example.realworld.articleservice.model.Article;
import dev.huhao.example.realworld.articleservice.service.ArticleService;
import dev.huhao.example.realworld.articleservice.service.exception.ArticleNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ArticleControllerTest extends ControllerTestBase {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleService articleService;

    @Nested
    @DisplayName("GET /articles/{slug}")
    class getArticle {

        @Test
        void should_get_article() throws Exception {
            // Given
            Article stubArticle = new Article();
            stubArticle.setSlug("fake-title");
            stubArticle.setTitle("Fake Title");
            stubArticle.setDescription("Description");
            stubArticle.setBody("Something");
            stubArticle.setCreatedAt(Instant.now());
            stubArticle.setUpdatedAt(Instant.now());
            stubArticle.setAuthorId(UUID.randomUUID());

            given(articleService.getArticle(stubArticle.getSlug())).willReturn(stubArticle);

            // When
            mvc.perform(get("/articles/" + stubArticle.getSlug()).accept(APPLICATION_JSON))
                    // Then
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                    .andExpect(jsonPath("$.slug", is(stubArticle.getSlug())))
                    .andExpect(jsonPath("$.title", is(stubArticle.getTitle())))
                    .andExpect(jsonPath("$.description", is(stubArticle.getDescription())))
                    .andExpect(jsonPath("$.body", is(stubArticle.getBody())))
                    .andExpect(jsonPath("$.authorId", is(stubArticle.getAuthorId().toString())))
                    .andExpect(jsonPath("$.createdAt", is(stubArticle.getCreatedAt().toString())))
                    .andExpect(jsonPath("$.updatedAt", is(stubArticle.getUpdatedAt().toString())));

        }

        @Test
        void should_404_with_message_when_not_found() throws Exception {
            // Given
            var slug = "fake-slug";
            var exception = new ArticleNotFoundException(slug);
            given(articleService.getArticle(any())).willThrow(exception);

            // When
            mvc.perform(get("/articles/" + slug).accept(APPLICATION_JSON))
                    // Then
                    .andExpect(status().isNotFound())
                    .andExpect(status().reason(exception.getMessage()));
        }
    }
}
