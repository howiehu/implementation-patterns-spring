package dev.huhao.example.realworld.articleservice.repository;

import dev.huhao.example.realworld.articleservice.model.Article;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleRepositoryTest extends RepositoryTestBase {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Nested
    class save {

        @Test
        void should_create_article_when_not_exist() {
            // Given
            var newArticle = new Article();
            newArticle.setSlug("fake-title");
            newArticle.setTitle("Fake Title");
            newArticle.setDescription("Description");
            newArticle.setBody("Something");
            var authorId = UUID.randomUUID();
            newArticle.setAuthorId(authorId);

            // When
            var result = articleRepository.save(newArticle);

            // Then
            assertThat(result.getSlug()).isEqualTo("fake-title");
            assertThat(result.getTitle()).isEqualTo("Fake Title");
            assertThat(result.getDescription()).isEqualTo("Description");
            assertThat(result.getBody()).isEqualTo("Something");
            assertThat(result.getAuthorId()).isEqualTo(authorId);
            assertThat(result.getCreatedAt()).isNotNull();
            assertThat(result.getUpdatedAt()).isNotNull();
        }
    }

    @Nested
    class findById {

        @Test
        void should_find_article_by_slug() {
            // Given
            var existingArticle = new Article();
            existingArticle.setSlug("fake-title");
            existingArticle.setTitle("Fake Title");
            existingArticle.setDescription("Description");
            existingArticle.setBody("Something");
            existingArticle.setCreatedAt(Instant.now());
            existingArticle.setUpdatedAt(Instant.now());
            existingArticle.setAuthorId(UUID.randomUUID());

            entityManager.persist(existingArticle);
            entityManager.flush();

            // When
            var result = articleRepository.findById(existingArticle.getSlug());

            // Then
            assertThat(result)
                    .hasValueSatisfying(v -> assertThat(v).usingRecursiveComparison().isEqualTo(existingArticle));
        }

        @Test
        void should_be_empty_when_no_existing_data() {
            // When
            var result = articleRepository.findById("");

            // Then
            assertThat(result).isEmpty();
        }

    }
}
