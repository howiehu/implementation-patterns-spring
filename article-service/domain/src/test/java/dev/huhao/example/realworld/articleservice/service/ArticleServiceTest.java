package dev.huhao.example.realworld.articleservice.service;

import dev.huhao.example.realworld.articleservice.model.Article;
import dev.huhao.example.realworld.articleservice.repository.ArticleRepository;
import dev.huhao.example.realworld.articleservice.service.exception.ArticleNotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ArticleServiceTest extends ServiceTestBase {

    @MockBean
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Nested
    class getArticle {

        @Test
        void should_get_article() {
            // Given
            var slug = "fake-slug";
            var stubArticle = mock(Article.class);
            given(articleRepository.findById(slug)).willReturn(Optional.of(stubArticle));

            // When
            var result = articleService.getArticle(slug);

            // Then
            assertThat(result).isEqualTo(stubArticle);
        }

        @Test
        void should_throw_ArticleNotFoundException_with_slug() {
            // Given
            var slug = "fake-slug";
            given(articleRepository.findById(slug)).willReturn(Optional.empty());

            // Then
            assertThatThrownBy(() ->
                    // When
                    articleService.getArticle(slug)
            ).isInstanceOf(ArticleNotFoundException.class).hasMessage("cannot find the article with slug " + slug);
        }
    }

    @Nested
    class createArticle {

        @Test
        void should_create_article() {
            // Given
            var stubArticle = new Article();
            var spyArticle = ArgumentCaptor.forClass(Article.class);
            var authorId = UUID.randomUUID();
            given(articleRepository.save(spyArticle.capture())).willReturn(stubArticle);

            // When
            var result = articleService.createArticle("Fake Title", "Description", "Something", authorId);

            // Then
            assertThat(result).isEqualTo(stubArticle);
            assertThat(spyArticle.getValue().getSlug()).isEqualTo("fake-title");
            assertThat(spyArticle.getValue().getTitle()).isEqualTo("Fake Title");
            assertThat(spyArticle.getValue().getDescription()).isEqualTo("Description");
            assertThat(spyArticle.getValue().getBody()).isEqualTo("Something");
            assertThat(spyArticle.getValue().getAuthorId()).isEqualTo(authorId);
        }
    }
}
